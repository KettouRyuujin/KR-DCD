package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.DCDmod;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;
import com.badlogic.gdx.graphics.Color;

public class Faiz_FAR_SoundsAndAnimation extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean FAR0Start = true;
	boolean FAR1Start = true;
	boolean FAR2Start = true;
	boolean FAR_faizStart = true;
	boolean start = true;
	public static String FAR_ATLAS = "img/char/DCD_Animational/FAR/FAR.atlas";
	public static String FAIZ2_ATLAS = "img/char/DCD_Animational/faiz/faiz2.atlas";
	public static String JSON36 = "img/char/DCD_Animational/faiz/faiz2_FAR0.json";
	public static String FAR_JSON3 = "img/char/DCD_Animational/FAR/FAR_FAR_faiz.json";

	
	public Faiz_FAR_SoundsAndAnimation(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
			//new AbstractAnimation("FAR0",FAIZ2_ATLAS,JSON36, 0.8f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
				new AbstractAnimation("FAR",FAR_ATLAS,Decade.FAR_JSON0, 1.0f, x , y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			}			
		}

		this.x = x;
		this.y = y;
		this.duration = 4.425F;//倒数时间
		this.startingDuration = 4.425F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		//AbstractAnimation FAR0 =  AbstractAnimation.getAnimation("FAR0");
		//FAR0.setMovable(false);
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			AbstractAnimation FAR =  AbstractAnimation.getAnimation("FAR");
			FAR.setMovable(false);
			this.duration -= Gdx.graphics.getDeltaTime();
			if(this.duration < 4.425F){
				if(FAR0Start) {
					//AbstractAnimation.changeAnimation(FAR0, FaizAnimationAction.faiz_FAR0);
		        	//FAR0.state.setAnimation(0, "FAR0", false);
					AbstractAnimation.changeAnimation(FAR, Decade.FAR0);
		        	FAR.state.setAnimation(0, "FAR0", false);
					FAR0Start = false;
				}
			}
			if(this.duration < 3.425F){
				if(FAR1Start) {
					AbstractAnimation.changeAnimation(FAR, Decade.FAR1);
		        	FAR.state.setAnimation(0, "FAR1", false);
					FAR1Start = false;
				}
			}
			if(this.duration < 2.075F){
				if(FAR_faizStart) {
					new AbstractAnimation("faiz",FAR_ATLAS,FAR_JSON3, 1.0f, x, y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
					AbstractAnimation faiz =  AbstractAnimation.getAnimation("faiz");
					faiz.setMovable(false);
					AbstractAnimation.changeAnimation(faiz, FaizAnimationAction.FAR_faiz);
					faiz.state.setAnimation(0, "FAR_faiz", false);
		        	FAR_faizStart = false;
		        	CardCrawlGame.sound.playA("FAR_FAIZ", 0F);
				}
			}
			if(this.duration < 1.875F){
				if(FAR2Start) {
					AbstractAnimation.changeAnimation(FAR, Decade.FAR2);
		        	FAR.state.setAnimation(0, "FAR2", false);
					FAR2Start = false;
				}
			}
			if (this.duration < 0.092F) {
				this.isDone = true;
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(33);//切换模型
			}
		}
		else {
			this.duration -= Gdx.graphics.getDeltaTime();

			if(this.duration < 2.075F){
				if(FAR_faizStart) {
					CardCrawlGame.sound.playA("FAR_FAIZ", 0F);
		        	FAR_faizStart = false;
				}
			}
			if (this.duration < 0.092F) {
				this.isDone = true;
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(33);//切换模型
			}
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(36);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}