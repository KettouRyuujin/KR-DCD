package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.graphics.Color;
import dcdmod.Characters.Decade;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_FAR_SoundsAndAnimation extends AbstractGameEffect {
	private float x;
	private float y;
	private int stage;

	public Kuuga_FAR_SoundsAndAnimation(float x, float y) {
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideKuugaPower")) {
			new AbstractAnimation("FAR", "img/char/DCD_Animation/FAR/FAR0.atlas","img/char/DCD_Animation/FAR/FAR0.json", 1.0f, x , y+15.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}
		this.x = x;
		this.y = y;
		this.duration = 3F;//倒数时间
		this.startingDuration = 3F;//持续时间
		this.stage = 0;
	}


	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideKuugaPower")){
			AbstractAnimation FAR =  AbstractAnimation.getAnimation("FAR");
			FAR.setMovable(false);

			if(this.duration < 3F && stage == 1){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR0);
				FAR.state.setAnimation(0, "FAR0", false);
				stage++;
			}
			else if(this.duration < 2.5F && stage == 2){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR1);
				FAR.state.setAnimation(0, "FAR1", false);
				stage++;
			}
			else if(this.duration < 1.15F && stage ==3){
				String FAR_DECADE_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_FAR_P.atlas";
				String FAR_DECADE_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_FAR_P.json";
				new AbstractAnimation("kuuga", FAR_DECADE_ATLAS, FAR_DECADE_JSON, 1.0f, x, y+40.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
				AbstractAnimation kuuga =  AbstractAnimation.getAnimation("kuuga");
				kuuga.setMovable(false);
				kuuga.state.setAnimation(0, "P", false);
				CardCrawlGame.sound.playA("FAR_KUUGA", 0.0f);
				stage++;
			}
			else if(this.duration < 0.95F && stage == 4){
				AbstractAnimation.changeAnimation(FAR, Decade.FAR2);
				FAR.state.setAnimation(0, "FAR2", false);
				stage++;
			}
			if (this.duration < 0.0F) {
				this.isDone = true;
			}
		}
		else{
			if (this.duration < 0.0F) {
				this.isDone = true;
				AbstractAnimation.clear("kuuga");
				AbstractAnimation.clear("FAR");
				CardCrawlGame.sound.playA("FAR_KUUGA", 0.0f);
			}
		}
	}


	public void render(SpriteBatch sb) {
		if(stage == 0){
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(105);
			stage ++;
		}
	}

	public void dispose() {
	}
}