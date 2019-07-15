package dcdmod.Helper;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import dcdmod.Patches.HibikiTaikoKeyEvent;


public class SpecialHibikiTaikoKey
{
    public static float TimerTime = 0.0F;

	public static int img0 = 0;
	public static int img1 = 0;
	public static int img2 = 0;
	public static int img3 = 0;
	public static int img4 = 0;
	public static String[] img = {
			"img/1024/orb-dark.png",//0
			"img/char/DCD_Animational/hibiki/taiko/CHAKA1.png",//1
			"img/char/DCD_Animational/hibiki/taiko/PON1.png",//2
			"img/char/DCD_Animational/hibiki/taiko/DON1.png",//3
			"img/char/DCD_Animational/hibiki/taiko/PATA1.png",//4
			"img/char/DCD_Animational/hibiki/taiko/CHAKA2.png",//5
			"img/char/DCD_Animational/hibiki/taiko/PON2.png",//6
			"img/char/DCD_Animational/hibiki/taiko/DON2.png",//7
			"img/char/DCD_Animational/hibiki/taiko/PATA2.png",//8
			"img/char/DCD_Animational/hibiki/taiko/CHAKA3.png",//9
			"img/char/DCD_Animational/hibiki/taiko/PON3.png",//10
			"img/char/DCD_Animational/hibiki/taiko/DON3.png",//11
			"img/char/DCD_Animational/hibiki/taiko/PATA3.png",//12
			"img/char/DCD_Animational/hibiki/taiko/CHAKA4.png",//13
			"img/char/DCD_Animational/hibiki/taiko/PON4.png",//14
			"img/char/DCD_Animational/hibiki/taiko/DON4.png",//15
			"img/char/DCD_Animational/hibiki/taiko/PATA4.png",//16
			"img/char/DCD_Animational/hibiki/taiko/CHAKA5.png",//17
			"img/char/DCD_Animational/hibiki/taiko/PON5.png",//18
			"img/char/DCD_Animational/hibiki/taiko/DON5.png",//19
			"img/char/DCD_Animational/hibiki/taiko/PATA5.png"};//20
	public static Texture img00 = ImageMaster.loadImage(img[img0]);
	public static Texture img11 = ImageMaster.loadImage(img[img1]);
	public static Texture img22 = ImageMaster.loadImage(img[img2]);
	public static Texture img33 = ImageMaster.loadImage(img[img3]);
	public static Texture img44 = ImageMaster.loadImage(img[img4]);

    
    public SpecialHibikiTaikoKey() {
		
    }
    
    public static void update() {
    	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
        	if(TimerTime > 0.0f) {
        		TimerTime -= Gdx.graphics.getDeltaTime();
        		for (int i = 0; i < HibikiTaikoKeyEvent.TaikoArray.length; i++) {
        			if(HibikiTaikoKeyEvent.TaikoArray[i] != "") {
        				int x = 0;
        				switch(HibikiTaikoKeyEvent.TaikoArray[i]) {
        				case "CHAKA":
        					x = 1;
        					break;
        				case "PON":
        					x = 2;
        					break;
        				case "DON":
        					x = 3;
        					break;
        				case "PATA":
        					x = 4;
        					break;
        				}
        				switch(i) {
        				case 0:
        					img0 = x+(i*4);
        					img00 = ImageMaster.loadImage(img[img0]);
        					break;
        				case 1:
        					img1 = x+(i*4);
        					img11 = ImageMaster.loadImage(img[img1]);
        					break;
        				case 2:
        					img2 = x+(i*4);
        					img22 =ImageMaster.loadImage(img[img2]);
        					break;
        				case 3:
        					img3 = x+(i*4);
        					img33 =ImageMaster.loadImage(img[img3]);
        					break;
        				case 4:
        					img4 = x+(i*4);
        					img44 =ImageMaster.loadImage(img[img4]);
        					break;
        				}
        			}
        		}
        	}
        	else if(TimerTime < 0.0F) {
        		timer();
        	}
    	}
    }
    
    public static void timer() {
    	if(!HibikiTaikoKeyEvent.Fever) {
    		switch(HibikiTaikoKeyEvent.TaikoArray[0]+HibikiTaikoKeyEvent.TaikoArray[1]+HibikiTaikoKeyEvent.TaikoArray[2]+HibikiTaikoKeyEvent.TaikoArray[3]+HibikiTaikoKeyEvent.TaikoArray[4]) {
			case "PONPONPATAPON"/*进攻*/:
				CardCrawlGame.sound.playA("Attack", 0.0f);
				taikocount();
				break;
			case "CHAKACHAKAPATAPON"/*防御*/:
				CardCrawlGame.sound.playA("Defend", 0.0f);
				taikocount();
				break;
			case "PATAPONDONCHAKA"/*解除异常状态*/:
				CardCrawlGame.sound.playA("Relieve", 0.0f);
				taikocount();
				break;
			case "PONPONCHAKACHAKA"/*蓄力*/:
					if (EnergyPanel.totalCount > 0) {
						CardCrawlGame.sound.playA("Accumulate", 0.0f);
						HibikiTaikoKeyEvent.TaikoAction("Accumulate");
						taikocount();
					}
					else {
						AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "能量不足，无法触发效果", true));
						HibikiTaikoKeyEvent.FeverOut(false);
					}
				break;
			case "PATAPONPATAPON"/*暂停*/:
				if(HibikiTaikoKeyEvent.ActionPoint>=6) {
					HibikiTaikoKeyEvent.TaikoAction("Suspend");
					taikocount();
				}
				else {
					HibikiTaikoKeyEvent.FeverOut(false);
				}
				break;
			default:
				HibikiTaikoKeyEvent.FeverOut(false);
				break;
			}
    	}
    	else {
    		HibikiTaikoKeyEvent.FeverOut(true);
    	}
    	HibikiTaikoKeyEvent.TaikoArray[0] = "";
    	HibikiTaikoKeyEvent.TaikoArray[1] = "";
    	HibikiTaikoKeyEvent.TaikoArray[2] = "";
    	HibikiTaikoKeyEvent.TaikoArray[3] = "";
    	HibikiTaikoKeyEvent.TaikoArray[4] = "";
    	HibikiTaikoKeyEvent.TaikoNumber = -1;
    	TimerTime = 0.0F;
    	img00 = ImageMaster.loadImage(img[img0]);
    	img11 = ImageMaster.loadImage(img[img1]);
    	img22 = ImageMaster.loadImage(img[img2]);
    	img33 = ImageMaster.loadImage(img[img3]);
    	img44 = ImageMaster.loadImage(img[img4]);
    }
    
    public static void taikocount() {
		HibikiTaikoKeyEvent.ComboPoint += 1;
		if(HibikiTaikoKeyEvent.ActionPoint>0) {
			HibikiTaikoKeyEvent.ActionPoint -=1;
		}
    }
    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img00, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        sb.draw(img11, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        sb.draw(img22, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        sb.draw(img33, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
        sb.draw(img44, 100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
    }
    
    
}
