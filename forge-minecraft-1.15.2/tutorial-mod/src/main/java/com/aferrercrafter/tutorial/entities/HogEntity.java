package com.aferrercrafter.tutorial.entities;

import com.aferrercrafter.tutorial.init.ModEntityTypes;
import com.aferrercrafter.tutorial.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.play.server.SEntityPropertiesPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HogEntity extends AnimalEntity {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, ModItems.RUBY.get());

    private EatGrassGoal eatGrassGoal;
    private int hogTimer;

    public HogEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.25F);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.250));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player){
        return 1 + this.world.rand.nextInt(4);
    }

    @Override
    protected SoundEvent getAmbientSound(){
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound(){
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source){
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn){
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return ModEntityTypes.HOG.get().create(this.world);
    }

    @Override
    protected void updateAITasks() {
        this.hogTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public void livingTick() {
        if(this.world.isRemote) {
            this.hogTimer = Math.max(0, this.hogTimer -1);
        }
        super.livingTick();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id){
        if(id == 10){
            this.hogTimer = 40;
        }
        else{
            super.handleStatusUpdate(id);
        }
    }

}
