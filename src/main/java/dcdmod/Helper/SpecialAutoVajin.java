package dcdmod.Helper;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.AnimationLoader;

public class SpecialAutoVajin
{

    public static boolean AutoVajinTrigger1 = true;

    public static int a;
    public static int cf;
    private static String AUTOVAJIN_ATLAS = "img/char/DCD_Animation/autovajin/autovajin.atlas";
    private static String AUTOVAJIN_JSON1 = "img/char/DCD_Animation/autovajin/autovajin_appear.json";
    private static String AUTOVAJIN_JSON2 = "img/char/DCD_Animation/autovajin/autovajin_attack.json";
    private static String AUTOVAJIN_JSON3 = "img/char/DCD_Animation/autovajin/autovajin_disappear.json";
    private static String AUTOVAJIN_JSON4 = "img/char/DCD_Animation/autovajin/autovajin_defend.json";
    private static String AUTOVAJIN_JSON5 = "img/char/DCD_Animation/autovajin/autovajin_normal.json";
    private static AnimationLoader AutoVajin1 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON1,  0.8f);
    private static AnimationLoader AutoVajin2 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON2,  0.8f);
    private static AnimationLoader AutoVajin3 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON3,  0.8f);
    private static AnimationLoader AutoVajin4 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON4,  0.8f);
    private static AnimationLoader AutoVajin5 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON5,  0.8f);
    
    
    public SpecialAutoVajin() {
    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
            if(AutoVajinTrigger1) {
                new AbstractSummonedAnimation("AutoVajin",AUTOVAJIN_ATLAS,AUTOVAJIN_JSON1, 0.8f, AbstractDungeon.player.drawX + 150.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                AutoVajinTrigger1 = false;
            }
        	AbstractSummonedAnimation AutoVajin =  AbstractSummonedAnimation.getAnimation("AutoVajin");
        	AutoVajin.setMovable(false);
        	if (a == cf) {
                System.out.println("==a");
                return;
            }
        	cf = a;
            if (a == 1) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin1);
            	AutoVajin.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin2);
            	AutoVajin.state.setAnimation(0, "attack", false);
            }
            else if (a == 3) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin3);
            	AutoVajin.state.setAnimation(0, "disappear", false);
                AutoVajinTrigger1 = true;
            }
            else if (a == 4) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin4);
            	AutoVajin.state.setAnimation(0, "defend", false);
            }
            else if(a == 5) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin5);
            	AutoVajin.state.setAnimation(0, "normal", true);
            }
    	}
    }
    

    
    public static void render() {

    }
    
}
