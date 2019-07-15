package dcdmod.Helper;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import dcdmod.Patches.HibikiTaikoKeyEvent;


public class SpecialHibikiTaikoScore
{
    private static float TimerTime = 0.0f;
	public static Hitbox hb;
    public static Texture img;
    public static float cX;
    public static float cY;
    public static float draw_width;
    public static float draw_height;
    public static int currentmX;
    public static int currentmY;
    public static boolean ishide = true;
    public static int a;
    public static String name;
    public static String hide;
    public static String Attack;
    public static String Attack2;
    public static String Attack3;
    public static String Defend;
    public static String Defend2;
    public static String Defend3;
    public static String Defend4;
    public static String Relieve;
    public static String Relieve2;
    public static String Accumulate;
    public static String Accumulate2;
    public static String Forward;
    public static String Back;
    public static String Fever;
    
    public SpecialHibikiTaikoScore() {
        draw_height = 19.0f;
        draw_width = 235.0f;
        currentmX = 0;
        currentmY = 0;
        SpecialHibikiTaikoScore.hb = new Hitbox(235.0F * Settings.scale, 284.0F * Settings.scale);
        SpecialHibikiTaikoScore.hb.move(200F * Settings.scale, AbstractDungeon.floorY + 350.0F * Settings.scale);
        SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taikoscore/Score.png");
		if (Settings.language == Settings.GameLanguage.ZHS||Settings.language == Settings.GameLanguage.ZHT) {
            name = "乐谱提示";
            hide = "左键点击可展开查看或收起 NL 右键可拖动卷轴";
            Attack = "所有敌人1点伤害4次，50%几率选择在手牌中加入1张响鬼相关的攻击卡,选择的卡牌为卡组中已有的卡时将搜寻至手牌并让其在使用前消耗为0";
            Attack2 = "所有敌人";
            Attack3 = "点伤害4次，50%几率选择在手牌中加入1张响鬼相关的攻击卡,选择的卡牌为卡组中已有的卡时将搜寻至手牌并让其在使用前消耗为0";
            Defend = "获得格挡4点，火焰屏障2层";
            Defend2 = "获得格挡";
            Defend3 = "点，火焰屏障";
            Defend4 = "层";
            Relieve = "随机解除1种DEBUFF或选择卡组中3张牌消耗，解除DEBUFF优先于烧牌";
            Relieve2 = "随机解除1种DEBUFF并可选择卡组中3张牌消耗";
            Accumulate = "将所有能量转化为AP，每点能量使AP+2，无能量时无法触发";
            Accumulate2 = "将所有能量转化为AP，每点能量使AP+2，最终获得AP+1，无能量时无法触发";
            Forward = "将所有AP点转化为力量，每点AP转化为1力量";
            Back = "将所有AP点转化为敏捷，每点AP转化为1敏捷";
            Fever = "消耗所有CP，获得相应层数的真红状态，进入响鬼红状态";
        }
        else {
            name = "RideBooker";
            Attack = "";
            Defend = "";
        }
    }
    
   
    
    public static void update() {
    	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
        	if(TimerTime > 0.0f) {
        		TimerTime -= Gdx.graphics.getDeltaTime();
        	}
        	else {
        		TimerTime = 0.0f;
        	}
        	if(InputHelper.isMouseDown_R && InputHelper.mX < SpecialHibikiTaikoScore.hb.x + SpecialHibikiTaikoScore.hb.width && InputHelper.mX > SpecialHibikiTaikoScore.hb.x && InputHelper.mY < SpecialHibikiTaikoScore.hb.y + SpecialHibikiTaikoScore.hb.height && InputHelper.mY > SpecialHibikiTaikoScore.hb.y){      
        		hb.move(InputHelper.mX,InputHelper.mY);
            }
        	if(CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.CARD_LIBRARY) {
        		if ((InputHelper.isMouseDown)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
        			if(AbstractDungeon.player.hasPower("KamenRideHibikiPower")&&TimerTime == 0.0f) {
        				hidescore();
        			}
                }	
        		if((!InputHelper.isMouseDown)&&(!InputHelper.isMouseDown_R)&&(InputHelper.mX<hb.x+hb.width)&&(InputHelper.mX>hb.x)&&(InputHelper.mY<hb.y+hb.height)&&(InputHelper.mY>hb.y)) {
        			if(ishide) {
        				TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide);
        			}
        			else {
        				if(HibikiTaikoKeyEvent.Fever) {
        					TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide + 
        							" NL " + " #y进攻 " + " NL " + Attack2+(HibikiTaikoKeyEvent.ComboPoint/5+1)+Attack3+ 
        							" NL " + " #y防御 " + " NL " + Defend2+(HibikiTaikoKeyEvent.ComboPoint/5+4)+Defend3+((HibikiTaikoKeyEvent.ComboPoint/5+4)/2)+Defend4+
        							" NL " + " #y净化 " + " NL " + Relieve2 +
        							" NL " + " #y蓄力 " + " NL " + Accumulate2 +
        							" NL " + " #y前进 " + " NL " + Forward +
        							" NL " + " #y后退 " + " NL " + Back +
        							" NL " + " #r狂热 " + " NL " + Fever );
        				}
        				else {
        					TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide + 
        							" NL " + " #y进攻 "+ " NL " + Attack +
        							" NL " + " #y防御 " + " NL " + Defend +
        							" NL " + " #y净化 " + " NL " + Relieve +
        							" NL " + " #y蓄力 " + " NL " + Accumulate);
        				}
        			}
        		}
        	}
    	}
    }
    
    public static void hidescore() {
		if(ishide) {
			if(HibikiTaikoKeyEvent.Fever) {
				SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taikoscore/FeverScore.png");
		        draw_height = 284.0f;
		        draw_width = 235.0f;
		        ishide = false;
			}
			else {
				SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taikoscore/UnfeverScore.png");
		        draw_height = 284.0f;
		        draw_width = 235.0f;
		        ishide = false;
			}
		}
		else {
			SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taikoscore/Score.png");
	        draw_height = 19.0f;
	        draw_width = 235.0f;
	        ishide = true;
		}
		TimerTime = 0.5f;
    }
    
    public static void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(SpecialHibikiTaikoScore.img,  hb.cX - (draw_width/2) , hb.cY - (draw_height/2) , draw_width/2, draw_height/2, draw_width/1, draw_height/1, Settings.scale, Settings.scale, 0.0f, 0, 0, (int)draw_width, (int)draw_height, false, false);
    }
    
    
}
