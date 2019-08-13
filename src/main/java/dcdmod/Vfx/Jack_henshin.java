package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Jack_henshin extends AbstractGameEffect {

	private boolean jackStart = true;
	private boolean jackEnd = true;
	private boolean tojack = true;

	public Jack_henshin(float x, float y) {
		String HENSHIN2_ATLAS = "img/char/DCD_Animation/blade/jack_henshin2.atlas";
		String JSON1 = "img/char/DCD_Animation/blade/jack_henshin2.json";
		new AbstractAnimation("JACK_HENSHIN2", HENSHIN2_ATLAS, JSON1,0.8f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 0.8f);


		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间

	}

	public void update() {
		AbstractAnimation JACK_HENSHIN;
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 2.8F) {
			if(jackEnd) {
				JACK_HENSHIN =  AbstractAnimation.getAnimation("JACK_HENSHIN2");
				JACK_HENSHIN.setMovable(false);
				JACK_HENSHIN.state.setAnimation(0, "henshin", false);
				jackEnd = false;
			}
		}
		if (this.duration < 0.25F) {
			if(tojack) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(46);//时间结束后切换回原本模型
				tojack = false;
			}
		}
		if(this.duration < 0.0F) {
			AbstractAnimation.clear("JACK_HENSHIN2");
			this.isDone = true;
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playA("blade_BGM2", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {

		if(jackStart) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(48);//切换模型
			CardCrawlGame.sound.playA("formride", 0.0f);
			CardCrawlGame.sound.playA("blade_jack", 0.0f);
			jackStart = false;
		}
	}

	public void dispose() {
	}
}