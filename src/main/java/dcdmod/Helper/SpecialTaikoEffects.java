package dcdmod.Helper;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.AnimationLoader;

public class SpecialTaikoEffects
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static boolean TAIKOTrigger1 = true;
    public static boolean TAIKOTrigger2 = true;
    public static int a;
    public static int cf;
    public static String TAIKO_ATLAS1 = "img/char/DCD_Animational/hibiki/taiko_appear.atlas";
    public static String TAIKO_ATLAS2 = "img/char/DCD_Animational/hibiki/taiko_p.atlas";
    public static String TAIKO_JSON1 = "img/char/DCD_Animational/hibiki/taiko_appear_appear.json";
    public static String TAIKO_JSON2 = "img/char/DCD_Animational/hibiki/taiko_p_taiko.json";
    public static String TAIKO_JSON3 = "img/char/DCD_Animational/hibiki/taiko_p_stop.json";
    public static String TAIKO_JSON4 = "img/char/DCD_Animational/hibiki/taiko_appear_disappear.json";
    public static AnimationLoader Taiko1 = new AnimationLoader(TAIKO_ATLAS1, TAIKO_JSON1,  0.8f);
    public static AnimationLoader Taiko2 = new AnimationLoader(TAIKO_ATLAS2, TAIKO_JSON2,  0.8f);
    public static AnimationLoader Taiko3 = new AnimationLoader(TAIKO_ATLAS2, TAIKO_JSON3,  0.8f);
    public static AnimationLoader Taiko4 = new AnimationLoader(TAIKO_ATLAS1, TAIKO_JSON4,  0.8f);
    
    public SpecialTaikoEffects() {
        draw_height = 84.0f;
        draw_width = 88.0f;
        currentmX = 0;
        currentmY = 0;
    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
    		if(TAIKOTrigger1) {
    			new AbstractSummonedAnimation("TAIKO",TAIKO_ATLAS1,TAIKO_JSON1, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
    			TAIKOTrigger1 = false;
    		}
        	AbstractSummonedAnimation TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO");
        	TAIKO.setMovable(false);
        	if (a == cf) {
                System.out.println(cf);
                return;
            }
        	cf = a;
            if (a == 1) {
            	AbstractSummonedAnimation.changeAnimation(TAIKO, Taiko1);
            	TAIKO.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
            	AbstractSummonedAnimation.changeAnimation(TAIKO, Taiko2);
            	TAIKO.state.setAnimation(0, "taiko", false);
            }
            else if (a == 3) {
            	AbstractSummonedAnimation.changeAnimation(TAIKO, Taiko3);
            	TAIKO.state.setAnimation(0, "stop", false);
            }
            else if (a == 4) {
            	AbstractSummonedAnimation.changeAnimation(TAIKO, Taiko4);
            	TAIKO.state.setAnimation(0, "disappear", false);
            }
    	}
    }
    

    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
}
