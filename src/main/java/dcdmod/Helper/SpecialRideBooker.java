package dcdmod.Helper;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.screens.mainMenu.*;
import dcdmod.Actions.AdventCardAction;
import dcdmod.Actions.RouzeCardAction;
import dcdmod.Card.Basic.KamenRide;
import com.megacrit.cardcrawl.helpers.input.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

public class SpecialRideBooker
{
    public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
	public static boolean haskamenpower = false;
	public static boolean nodecade = false;
	public static boolean decadenextturn = true;
	public static boolean ryukinextturn = true;
	public static boolean bladenextturn = true;
	public static int kamenpowerpoint = 0;
	public static int currentpoint = 0;
	public static int decadepoint = 0;
	public static int ryukipoint = 0;
	public static int bladepoint = 0;
    public static String name;
    public static String firstline;
    public static String secenoline;
    
    public SpecialRideBooker() {
        draw_height = 84.0f;
        draw_width = 88.0f;
        currentmX = 0;
        currentmY = 0;
        SpecialRideBooker.hb = new Hitbox(120.0F * Settings.scale, 120.0F * Settings.scale);
        SpecialRideBooker.img = ImageMaster.loadImage("img/char/DCD/RideBooker.png");
        SpecialRideBooker.hb.move(96.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        if (Settings.language == Settings.GameLanguage.ZHS||Settings.language == Settings.GameLanguage.ZHT) {
            name = "RideBooker";
            firstline = "能够按照Decade的意思抽出任意的骑士卡片。与克莱因之壶相通，藏有无尽的卡片及能量源。";
            secenoline = "可使用次数：";
        }
        else {
            name = "RideBooker";
            firstline = "";
            secenoline = "";
        }
    }
    
    public static void update() {
    	if(AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
    		if(decadenextturn) {
    			currentpoint = 0;
    		}
        	else {
        		currentpoint = decadepoint;
        	}
    	}
    	else if(AbstractDungeon.player.hasPower("KamenRideRyukiPower")) {
    		if(ryukinextturn) {
    			currentpoint = 0;
    		}
        	else {
        		currentpoint = ryukipoint;
        	}
    	}
    	else if(AbstractDungeon.player.hasPower("KamenRideBladePower")||AbstractDungeon.player.hasPower("BladeJackPower")) {
    		if(bladenextturn) {
    			currentpoint = 0;
    		}
        	else {
        		currentpoint = bladepoint;
        	}
    	}
    	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
        	if(InputHelper.isMouseDown && InputHelper.mX < SpecialRideBooker.hb.x + SpecialRideBooker.hb.width && InputHelper.mX > SpecialRideBooker.hb.x && InputHelper.mY < SpecialRideBooker.hb.y + SpecialRideBooker.hb.height && InputHelper.mY > SpecialRideBooker.hb.y){      
        		hb.move(InputHelper.mX,InputHelper.mY);
            }
            if ((InputHelper.isMouseDown_R)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
            	if(AbstractDungeon.player.hasPower("KamenRideDecadePower")&&haskamenpower) {
    				AbstractCard c = new KamenRide();
    				c.freeToPlayOnce = true;
    				//c.purgeOnUse = true;
    				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1)); 
    				//AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null, c.energyOnUse));
    				decadenextturn = false;
    				updatecurrentpoint();
    				decadepoint = currentpoint;
    			}
            	else if(AbstractDungeon.player.hasPower("KamenRideRyukiPower")&&haskamenpower) {
    				AdventCardAction.VentCard();
    				ryukinextturn = false;
    				haskamenpower = false;
    			}
            	else if((AbstractDungeon.player.hasPower("KamenRideBladePower")||AbstractDungeon.player.hasPower("BladeJackPower"))&&haskamenpower) {
    				RouzeCardAction.RouzeCard();;
    				bladenextturn = false;
    				haskamenpower = false;
            	}
            }
			if(currentpoint < kamenpowerpoint){haskamenpower = true;}
			else {haskamenpower = false;}
			if((!InputHelper.isMouseDown)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
				TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + " NL " + secenoline + currentpoint + "/" + kamenpowerpoint);
			}
    	}
    	AdventCardAction.update();
    	RouzeCardAction.update();
	}
    

    public static void updatecurrentpoint() {++currentpoint;}
    
    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(SpecialRideBooker.img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
}
