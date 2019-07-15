package dcdmod.Helper;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.FaizGearAction;


public class SpecialFaizButton
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static boolean haskamenpower = true;
    public static int a;
    public static String name;
    public static String firstline;
    public static String secenoline;
    public static String tripleline;
    
    public SpecialFaizButton() {
        draw_height = 55.0f;
        draw_width = 188.0f;
        currentmX = 0;
        currentmY = 0;
        SpecialFaizButton.hb = new Hitbox(120.0F * Settings.scale, 120.0F * Settings.scale);
        SpecialFaizButton.hb.move(198.0F * Settings.scale, 300.0F * Settings.scale);
        SpecialFaizButton.img = ImageMaster.loadImage("img/char/DCD/EnterButton.png");
		if (Settings.language == Settings.GameLanguage.ZHS||Settings.language == Settings.GameLanguage.ZHT) {
            name = "充能按键";
            firstline = "左键点击可激发插件的主动效果 NL ";
            secenoline = "右键点击可向插件注入光子血液，恢复冷却 NL ";
            tripleline = " #r光子血液 ： NL ";
        }
        else {
            name = "RideBooker";
            firstline = "";
            secenoline = "";
        }
    }
    
   
    
    public static void update() {
    	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
    		if ((InputHelper.isMouseDown)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
    			if(AbstractDungeon.player.hasPower("KamenRideFaizPower")&&haskamenpower) {
    				EnterButtonAction.EnterButton();
    				haskamenpower = false;
    			}
    		}
    		if ((InputHelper.isMouseDown_R)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
    			if(AbstractDungeon.player.hasPower("KamenRideFaizPower")&&haskamenpower) {
    				FaizGearAction.FaizGear();
    				haskamenpower = false;
    			}
    		}
    		if((!InputHelper.isMouseDown)&&(!InputHelper.isMouseDown_R)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
    			TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + secenoline + tripleline + FaizGearAction.FaizPoint);
    		}
    	}
    	EnterButtonAction.update();
    	FaizGearAction.update();
    }
    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(SpecialFaizButton.img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
    
}
