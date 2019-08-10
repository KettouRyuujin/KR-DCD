package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.DCDmod;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Faiz_FAR_SoundsAndAnimation extends AbstractGameEffect {
	private float x;
	private float y;
	private boolean FAR0Start = true;
	private boolean FAR1Start = true;
	private boolean FAR2Start = true;
	private boolean FAR_faizStart = true;
	private boolean start = true;


	public Faiz_FAR_SoundsAndAnimation(float x, float y) {
		//new AbstractAnimation("FAR0",FAIZ2_ATLAS,JSON36, 0.8f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			new AbstractAnimation("FAR","img/char/DCD_Animation/FAR/FAR0.atlas","img/char/DCD_Animation/FAR/FAR0.json", 1.0f, x , y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}

		this.x = x;
		this.y = y;
		this.duration = 4.425F;//倒数时间
		this.startingDuration = 4.425F;//持续时间
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
					String FAR_FAIZ_ATLAS = "img/char/DCD_Animation/faiz/FAR_faiz.atlas";
					String FAR_FAIZ_JSON = "img/char/DCD_Animation/faiz/FAR_faiz.json";
					new AbstractAnimation("faiz", FAR_FAIZ_ATLAS, FAR_FAIZ_JSON, 1.0f, x, y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
					AbstractAnimation faiz =  AbstractAnimation.getAnimation("faiz");
					faiz.setMovable(false);
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
				AbstractAnimation.clear("faiz");
				AbstractAnimation.clear("FAR");
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
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(36);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}