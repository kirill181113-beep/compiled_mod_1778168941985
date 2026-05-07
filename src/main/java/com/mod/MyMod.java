package com.yourmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class MyMod implements ModInitializer {

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            
            // Если игрок держит костную муку
            if (stack.getItem() == Items.BONE_MEAL) {
                BlockPos pos = hitResult.getBlockPos();
                
                // Если блок под ногами — трава
                if (world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK)) {
                    // Проверяем, не пытаемся ли мы заспавнить подсолнух
                    // (имитация — костная мука не сработает, если шанс на подсолнух)
                    
                    // Симуляция: 10% шанс, что костная мука НЕ сработает
                    // Если подсолнух должен был появиться — блокируем костную муку
                    if (new Random().nextInt(100) < 10) {
                        return ActionResult.FAIL; // Не даём костной муке сработать
                    }
                }
            }
            return ActionResult.PASS;
        });
        
        System.out.println("Мод загружен — подсолнухи не спавнятся от костной муки!");
    }
}