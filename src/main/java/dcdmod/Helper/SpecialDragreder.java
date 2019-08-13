package dcdmod.Helper;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;

public class SpecialDragreder
{

    public static int a;
    public static int cf;


    public SpecialDragreder() {

    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
        	if (a == cf) {
                System.out.println("==a");
                return;
            }
        	cf = a;
            AbstractSummonedAnimation DRAGREDER;
            String DRAGREDER_ATLAS = "img/char/DCD_Animation/dragreder/dragreder.atlas";
            if (a == 1) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON1 = "img/char/DCD_Animation/dragreder/dragreder_appear.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON1, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON2 = "img/char/DCD_Animation/dragreder/dragreder_attack.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON2, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "attack", false);
            }
            else if (a == 3) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON3 = "img/char/DCD_Animation/dragreder/dragreder_defendtonormal.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON3, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "defendtonormal", false);
            }
            else if (a == 4) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON4 = "img/char/DCD_Animation/dragreder/dragreder_disappear.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON4, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "disappear", false);
            }
            else if (a == 5) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON5 = "img/char/DCD_Animation/dragreder/dragreder_normal.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON5, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "normal", true);
            }
            else if (a == 6) {
                AbstractSummonedAnimation.clear("DRAGREDER");
                String DRAGREDER_JSON6 = "img/char/DCD_Animation/dragreder/dragreder_normaltodefend.json";
                new AbstractSummonedAnimation("DRAGREDER", DRAGREDER_ATLAS, DRAGREDER_JSON6, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
                DRAGREDER.setMovable(false);
            	DRAGREDER.state.setAnimation(0, "normaltodefend", false);
            }
    	}
    }
    

    
    public static void render() {

    }
    
}
