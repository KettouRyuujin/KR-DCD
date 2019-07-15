package dcdmod.Helper;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

import dcdmod.Patches.HibikiTaikoKeyEvent;


public class SpecialHibikiTaiko
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static int a;
    public static String name;
    public static String firstline;
    public static String secenoline;
    public static String tripleline;
    public static String Fourthline;
    public static String Fifthline;
    
    public SpecialHibikiTaiko() {
        draw_height = 126.0f;
        draw_width = 709.0f;
        currentmX = 0;
        currentmY = 0;
        SpecialHibikiTaiko.hb = new Hitbox(709.0f, 126.0F);
        SpecialHibikiTaiko.hb.move(500.0F * Settings.scale, AbstractDungeon.floorY + 463.0F * Settings.scale);
        SpecialHibikiTaiko.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taiko/taiko1.png");
		if (Settings.language == Settings.GameLanguage.ZHS||Settings.language == Settings.GameLanguage.ZHT) {
            name = "鼓动界面";
            firstline = "通过按键U、I、O、P敲出不同的音色，通过特定的敲击顺序触发效果 NL ";
            secenoline = "正确的敲击音色后通过按下空格键或等待其自动触发将消耗储存的能量触发特殊效果 NL ";
            tripleline = "自动触发时连击点数（CP）+1,通过按下空格键触发时CP+2,触发失败则-1。CP达到10进入FEVER状态 NL 现在的ComboPoint为：";
            Fourthline = "点 NL 每触发成功1次特殊效果扣除1点行动点数（AP），现在的ActionPoint为：";
            Fifthline = "点";
        }
        else {
            name = "RideBooker";
            firstline = "";
            secenoline = "";
        }
    }
    
   
    
    public static void update() {
    	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
    		if((!InputHelper.isMouseDown)&&(!InputHelper.isMouseDown_R)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
    			TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + secenoline + tripleline + HibikiTaikoKeyEvent.ComboPoint + Fourthline + HibikiTaikoKeyEvent.ActionPoint + Fifthline);
    		}
    	}
    }
    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(SpecialHibikiTaiko.img, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        //sb.draw(SpecialHibikiTaiko.img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
    
}
