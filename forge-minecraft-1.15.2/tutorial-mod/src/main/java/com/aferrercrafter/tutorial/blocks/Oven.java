package com.aferrercrafter.tutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Oven extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6, 11, 16, 10, 14, 17),
            Block.makeCuboidShape(15, 0, 0, 16, 2, 1),
            Block.makeCuboidShape(15, 0, 15, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 15, 1, 2, 16),
            Block.makeCuboidShape(1, 1, 1, 15, 8, 15),
            Block.makeCuboidShape(0, 0, 0, 1, 2, 1),
            Block.makeCuboidShape(1, 1, 15, 2, 2, 16),
            Block.makeCuboidShape(14, 1, 15, 15, 2, 16),
            Block.makeCuboidShape(14, 1, 0, 15, 2, 1),
            Block.makeCuboidShape(1, 1, 0, 2, 2, 1),
            Block.makeCuboidShape(0, 8, 0, 16, 9, 16),
            Block.makeCuboidShape(2, 9, 2, 14, 10, 14),
            Block.makeCuboidShape(6, 9, 13, 10, 14, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6, 11, -1, 10, 14, 0),
            Block.makeCuboidShape(0, 0, 15, 1, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 2, 1),
            Block.makeCuboidShape(15, 0, 0, 16, 2, 1),
            Block.makeCuboidShape(1, 1, 1, 15, 8, 15),
            Block.makeCuboidShape(15, 0, 15, 16, 2, 16),
            Block.makeCuboidShape(14, 1, 0, 15, 2, 1),
            Block.makeCuboidShape(1, 1, 0, 2, 2, 1),
            Block.makeCuboidShape(1, 1, 15, 2, 2, 16),
            Block.makeCuboidShape(14, 1, 15, 15, 2, 16),
            Block.makeCuboidShape(0, 8, 0, 16, 9, 16),
            Block.makeCuboidShape(2, 9, 2, 14, 10, 14),
            Block.makeCuboidShape(6, 9, 0, 10, 14, 3)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(-1, 11, 6, 0, 14, 10),
            Block.makeCuboidShape(15, 0, 15, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 15, 1, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 2, 1),
            Block.makeCuboidShape(1, 1, 1, 15, 8, 15),
            Block.makeCuboidShape(15, 0, 0, 16, 2, 1),
            Block.makeCuboidShape(0, 1, 1, 1, 2, 2),
            Block.makeCuboidShape(0, 1, 14, 1, 2, 15),
            Block.makeCuboidShape(15, 1, 14, 16, 2, 15),
            Block.makeCuboidShape(15, 1, 1, 16, 2, 2),
            Block.makeCuboidShape(0, 8, 0, 16, 9, 16),
            Block.makeCuboidShape(2, 9, 2, 14, 10, 14),
            Block.makeCuboidShape(0, 9, 6, 3, 14, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(16, 11, 6, 17, 14, 10),
            Block.makeCuboidShape(0, 0, 0, 1, 2, 1),
            Block.makeCuboidShape(15, 0, 0, 16, 2, 1),
            Block.makeCuboidShape(15, 0, 15, 16, 2, 16),
            Block.makeCuboidShape(1, 1, 1, 15, 8, 15),
            Block.makeCuboidShape(0, 0, 15, 1, 2, 16),
            Block.makeCuboidShape(15, 1, 14, 16, 2, 15),
            Block.makeCuboidShape(15, 1, 1, 16, 2, 2),
            Block.makeCuboidShape(0, 1, 1, 1, 2, 2),
            Block.makeCuboidShape(0, 1, 14, 1, 2, 15),
            Block.makeCuboidShape(0, 8, 0, 16, 9, 16),
            Block.makeCuboidShape(2, 9, 2, 14, 10, 14),
            Block.makeCuboidShape(13, 9, 6, 16, 14, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public Oven(){
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.5f, 4.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context){
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6f;
    }
}
