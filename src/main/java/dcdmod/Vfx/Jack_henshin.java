package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

import com.badlogic.gdx.graphics.Color;

public class Jack_henshin extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean jackStart = true;
	boolean jackEnd = true;
	boolean tojack = true;
	public static String HENSHIN2_ATLAS = "img/char/DCD_Animation/blade/jack_henshin2.atlas";
	public static String JSON1 = "img/char/DCD_Animation/blade/jack_henshin2.json";
	
	public Jack_henshin(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
			new AbstractAnimation("JACK_HENSHIN2",HENSHIN2_ATLAS,JSON1,0.8f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 0.8f);
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		AbstractAnimation JACK_HENSHIN =  null;
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
			this.isDone = true;
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playA("blade_BGM2", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
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