package dcdmod.Helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import dcdmod.Actions.TurnTimer;
import dcdmod.Patches.AbstractSummonedAnimation;

public class SpecialTaikoEffects
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    private static float draw_width;
    private static float draw_height;
    public static int a;
    public static int cf;

    public SpecialTaikoEffects() {
        draw_height = 84.0f;
        draw_width = 88.0f;
    }
    
   
    
    public static void update() {
    	if(!TurnTimer.BattleEnd) {
        	if (a == cf) {
                System.out.println(cf);
                return;
            }
        	cf = a;
            AbstractSummonedAnimation TAIKO;
            String TAIKO_ATLAS1 = "img/char/DCD_Animation/hibiki/taiko_appear.atlas";
            String TAIKO_ATLAS2 = "img/char/DCD_Animation/hibiki/taiko_p.atlas";
            if (a == 1) {
                AbstractSummonedAnimation.clear("TAIKO");
                String TAIKO_JSON1 = "img/char/DCD_Animation/hibiki/taiko_appear_appear.json";
                new AbstractSummonedAnimation("TAIKO", TAIKO_ATLAS1, TAIKO_JSON1, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO");
                TAIKO.setMovable(false);
            	TAIKO.state.setAnimation(0, "appear", false);
            }
            else if (a == 2) {
                AbstractSummonedAnimation.clear("TAIKO");
                String TAIKO_JSON2 = "img/char/DCD_Animation/hibiki/taiko_p_taiko.json";
                new AbstractSummonedAnimation("TAIKO", TAIKO_ATLAS2, TAIKO_JSON2, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO");
                TAIKO.setMovable(false);
            	TAIKO.state.setAnimation(0, "taiko", false);
            }
            else if (a == 3) {
                AbstractSummonedAnimation.clear("TAIKO");
                String TAIKO_JSON3 = "img/char/DCD_Animation/hibiki/taiko_p_stop.json";
                new AbstractSummonedAnimation("TAIKO", TAIKO_ATLAS2, TAIKO_JSON3, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO");
                TAIKO.setMovable(false);
            	TAIKO.state.setAnimation(0, "stop", false);
            }
            else if (a == 4) {
                AbstractSummonedAnimation.clear("TAIKO");
                String TAIKO_JSON4 = "img/char/DCD_Animation/hibiki/taiko_appear_disappear.json";
                new AbstractSummonedAnimation("TAIKO", TAIKO_ATLAS1, TAIKO_JSON4, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
                TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO");
                TAIKO.setMovable(false);
            	TAIKO.state.setAnimation(0, "disappear", false);
            }
    	}
    }
    

    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
}
