package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractAnimation;

public class Decade_FAR_SoundsAndAnimation extends AbstractGameEffect {
	private float x;
	private float y;
	private boolean FAR_start = true;
	private boolean FAR0 = true;
	private boolean FAR1 = true;
	private boolean FAR2 = true;
	private boolean FAR_Decade = true;

	public Decade_FAR_SoundsAndAnimation(float x, float y) {
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
			new AbstractAnimation("FAR", "img/char/DCD_Animation/FAR/FAR0.atlas","img/char/DCD_Animation/FAR/FAR0.json", 1.0f, x , y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}
		this.x = x;
		this.y = y;
		this.duration = 3F;//倒数时间
		this.startingDuration = 3F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideDecadePower")){
			AbstractAnimation FAR =  AbstractAnimation.getAnimation("FAR");
			FAR.setMovable(false);

			if(this.duration < 3F && FAR0){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR0);
				FAR.state.setAnimation(0, "FAR0", false);
				FAR0 = false;
			}
			if(this.duration < 2.5F && FAR1){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR1);
				FAR.state.setAnimation(0, "FAR1", false);
				FAR1 = false;
			}

			if(this.duration < 1.15F && FAR_Decade){
				String FAR_DECADE_ATLAS = "img/char/DCD_Animation/decade/FAR/FAR_decade.atlas";
				String FAR_DECADE_JSON = "img/char/DCD_Animation/decade/FAR/FAR_decade.json";
				new AbstractAnimation("decade", FAR_DECADE_ATLAS, FAR_DECADE_JSON, 1.0f, x, y+40.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
				AbstractAnimation decade =  AbstractAnimation.getAnimation("decade");
				decade.setMovable(false);
				decade.state.setAnimation(0, "FAR_decade", false);
				CardCrawlGame.sound.playA("FAR_DCD", 0.0f);
				CardCrawlGame.sound.playA("decade_BGM", 0.0f);
				FAR_Decade = false;
			}
			if(this.duration < 0.95F && FAR2){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR2);
				FAR.state.setAnimation(0, "FAR2", false);
				FAR2 = false;
			}
			if (this.duration < 0.0F) {
				this.isDone = true;
			}
		}
		else{
			if (this.duration < 0.0F) {
				this.isDone = true;
				AbstractAnimation.clear("decade");
				AbstractAnimation.clear("FAR");
				CardCrawlGame.sound.playA("FAR_DCD", 0.0f);
			}
		}

	}

	public void render(SpriteBatch sb) {
		if(FAR_start){
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(75);
			FAR_start = false;
		}
	}

	public void dispose() {
	}
}