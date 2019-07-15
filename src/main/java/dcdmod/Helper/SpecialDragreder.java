package dcdmod.Helper;

import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.AnimationLoader;

public class SpecialDragreder
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static boolean DRAGREDERTrigger1 = true;
    public static boolean DRAGREDERTrigger2 = true;
    public static int a;
    public static int cf;
    public static String DRAGREDER_ATLAS = "img/char/DCD_Animational/dragreder/dragreder.atlas";
    public static String DRAGREDER_JSON1 = "img/char/DCD_Animational/dragreder/dragreder_appear.json";
    public static String DRAGREDER_JSON2 = "img/char/DCD_Animational/dragreder/dragreder_attack.json";
    public static String DRAGREDER_JSON3 = "img/char/DCD_Animational/dragreder/dragreder_defendtonormal.json";
    public static String DRAGREDER_JSON4 = "img/char/DCD_Animational/dragreder/dragreder_disappear.json";
    public static String DRAGREDER_JSON5 = "img/char/DCD_Animational/dragreder/dragreder_normal.json";
    public static String DRAGREDER_JSON6 = "img/char/DCD_Animational/dragreder/dragreder_normaltodefend.json";
    public static AnimationLoader Dragreder1 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON1,  0.8f);
    public static AnimationLoader Dragreder2 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON2,  0.8f);
    public static AnimationLoader Dragreder3 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON3,  0.8f);
    public static AnimationLoader Dragreder4 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON4,  0.8f);
    public static AnimationLoader Dragreder5 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON5,  0.8f);
    public static AnimationLoader Dragreder6 = new AnimationLoader(DRAGREDER_ATLAS, DRAGREDER_JSON6,  0.8f);
    
    public SpecialDragreder() {
        draw_height = 84.0f;
        draw_width = 88.0f;
        currentmX = 0;
        currentmY = 0;
    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
    		if(DRAGREDERTrigger1) {
            	new AbstractSummonedAnimation("DRAGREDER",DRAGREDER_ATLAS,DRAGREDER_JSON1, 0.8f, AbstractDungeon.player.drawX + 300.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
            	DRAGREDERTrigger1 = false;
    		}
        	AbstractSummonedAnimation DRAGREDER =  AbstractSummonedAnimation.getAnimation("DRAGREDER");
        	DRAGREDER.setMovable(false);
        	if (a == cf) {
                System.out.println("==a");
                return;
            }
        	cf = a;
            if (a == 1) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder1);
            	DRAGREDER.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder2);
            	DRAGREDER.state.setAnimation(0, "attack", false);
            }
            else if (a == 3) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder3);
            	DRAGREDER.state.setAnimation(0, "defendtonormal", false);
            }
            else if (a == 4) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder4);
            	DRAGREDER.state.setAnimation(0, "disappear", false);
            }
            else if (a == 5) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder5);
            	DRAGREDER.state.setAnimation(0, "normal", true);
            }
            else if (a == 6) {
            	AbstractSummonedAnimation.changeAnimation(DRAGREDER, Dragreder6);
            	DRAGREDER.state.setAnimation(0, "normaltodefend", false);
            }
    	}
    }
    

    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
}
