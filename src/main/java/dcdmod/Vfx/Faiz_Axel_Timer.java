package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.FaizGearAction;
import dcdmod.Characters.Decade;

import com.badlogic.gdx.graphics.Color;

public class Faiz_Axel_Timer extends AbstractGameEffect {
	
	private float x;
	private float y;
	private static Texture img1 = null;
	private Texture img2 = null;
	private int img0 = 100;
	private static float TimerTime = 0.1F;
	private static String Timer[] = {
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-101.png",//0
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-1.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-2.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-3.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-4.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-5.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-6.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-7.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-8.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-9.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-10.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-11.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-12.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-13.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-14.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-15.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-16.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-17.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-18.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-19.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-20.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-21.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-22.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-23.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-24.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-25.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-26.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-27.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-28.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-29.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-30.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-31.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-32.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-33.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-34.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-35.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-36.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-37.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-38.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-39.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-40.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-41.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-42.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-43.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-44.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-45.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-46.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-47.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-48.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-49.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-50.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-51.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-52.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-53.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-54.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-55.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-56.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-57.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-58.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-59.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-60.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-61.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-62.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-63.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-64.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-65.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-66.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-67.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-68.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-69.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-70.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-71.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-72.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-73.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-74.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-75.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-76.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-77.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-78.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-79.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-80.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-81.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-82.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-83.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-84.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-85.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-86.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-87.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-88.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-89.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-90.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-91.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-92.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-93.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-94.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-95.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-96.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-97.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-98.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-99.png",
			"img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-0.png"};
	boolean timeout = true;
	private static int img;
    
	public Faiz_Axel_Timer(float x, float y) {
		if (Faiz_Axel_Timer.img1 == null) {
			Faiz_Axel_Timer.img1 = new Texture(Gdx.files.internal("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-0.png"));
		}
		if (this.img2 == null) {
			this.img2 = new Texture(Gdx.files.internal("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-190.png"));
		}
		this.x = x;
		this.y = y;
		Faiz_Axel_Timer.img = img0;
		this.duration = 11.35F;//倒数时间
		this.startingDuration = 11.35F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!EnterButtonAction.AxelForm) {
			this.isDone = true;
			if(Decade.cf != 10 && Decade.cf != 1 && Decade.cf != 3) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_backtofaiz(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 0.0F));
			}
		}
		if (this.duration < 11.3F) {
	    	if(TimerTime > 0.0f) {
	    		TimerTime -= Gdx.graphics.getDeltaTime();
	    	}
	    	if(TimerTime < 0.0F) {
	    		timer();
	    	}
    	}
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("axel_timeout", 0F);
			EnterButtonAction.AxelForm = false;
			EnterButtonAction.FaizPhone = false;
			EnterButtonAction.FaizPointer = false;
			EnterButtonAction.FaizShot = false;
			EnterButtonAction.FaizEdge = false;
			FaizGearAction.FaizPoint = 0;
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_backtofaiz(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 0.0F));
		}
	}

	public static void timer() {
		if(Faiz_Axel_Timer.img != 0) {
			TimerTime = 0.1f;
			Faiz_Axel_Timer.img -=1;
			img1 = ImageMaster.loadImage(Timer[Faiz_Axel_Timer.img]);
			switch(Faiz_Axel_Timer.img) {
			case 99:
				case 90:
				case 80:
				case 70:
				case 60:
				case 50:
				case 40:
					CardCrawlGame.sound.playA("axel_timer", 0F);
				break;
				case 30:
				CardCrawlGame.sound.playA("axel_3", 0F);
				break;
			case 20:
				CardCrawlGame.sound.playA("axel_2", 0F);
				break;
			case 10:
				CardCrawlGame.sound.playA("axel_1", 0F);
				break;
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img2, this.x-100.0f, this.y-22.0f);
		sb.setColor(this.color);
		sb.draw(Faiz_Axel_Timer.img1, this.x, this.y);
	}

	public void dispose() {
	}
}