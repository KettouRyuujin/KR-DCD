package dcdmod.Helper;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.AnimationLoader;

public class SpecialAutoVajin
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static boolean AutoVajinTrigger1 = true;
    public static boolean AutoVajinTrigger2 = true;
    public static int a;
    public static int cf;
    public static String AUTOVAJIN_ATLAS = "img/char/DCD_Animational/autovajin/autovajin.atlas";
    public static String AUTOVAJIN_JSON1 = "img/char/DCD_Animational/autovajin/autovajin_appear.json";
    public static String AUTOVAJIN_JSON2 = "img/char/DCD_Animational/autovajin/autovajin_attack.json";
    public static String AUTOVAJIN_JSON3 = "img/char/DCD_Animational/autovajin/autovajin_disappear.json";
    public static String AUTOVAJIN_JSON4 = "img/char/DCD_Animational/autovajin/autovajin_defend.json";
    public static String AUTOVAJIN_JSON5 = "img/char/DCD_Animational/autovajin/autovajin_stop.json";
    public static AnimationLoader AutoVajin1 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON1,  0.8f);
    public static AnimationLoader AutoVajin2 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON2,  0.8f);
    public static AnimationLoader AutoVajin3 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON3,  0.8f);
    public static AnimationLoader AutoVajin4 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON4,  0.8f);
    public static AnimationLoader AutoVajin5 = new AnimationLoader(AUTOVAJIN_ATLAS, AUTOVAJIN_JSON5,  0.8f);
    
    
    public SpecialAutoVajin() {
        draw_height = 84.0f;
        draw_width = 88.0f;
        currentmX = 0;
        currentmY = 0;
        //AutoVajin.hb = new Hitbox(120.0F * Settings.scale, 120.0F * Settings.scale);
        //AutoVajin.img = ImageMaster.loadImage("img/char/DCD/RideBooker.png");
        //AutoVajin.hb.move(96.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        
    }
    
   
    
    public static void update() {
        //String MY_CHARACTER_SKELETON_ATLAS = AUTOVAJIN_ATLAS;
        //String MY_CHARACTER_SKELETON_JSON = AUTOVAJIN_JSON1;
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
            }
            else if (a == 4) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin4);
            	AutoVajin.state.setAnimation(0, "defend", false);
            }
            else if(a == 5) {
            	AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin5);
            	AutoVajin.state.setAnimation(0, "stop", false);
            }
    	}
    }
    

    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
}
