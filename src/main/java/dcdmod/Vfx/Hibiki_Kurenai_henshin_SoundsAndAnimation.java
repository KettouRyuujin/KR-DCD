package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Hibiki_Kurenai_henshin_SoundsAndAnimation extends AbstractGameEffect {

	private boolean HENSHIN1Start = true;
	private boolean HENSHIN2Start = true;
	private boolean HENSHIN3Start = true;
	private boolean HENSHIN4Start = true;


	public Hibiki_Kurenai_henshin_SoundsAndAnimation(float x, float y) {


		String HENSHIN1_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_kurenai_r.atlas";
		String JSON1 = "img/char/DCD_Animation/hibiki/hibiki_kurenai_r.json";
		new AbstractAnimation("HIBIKI_KURENAI_HENSHIN1", HENSHIN1_ATLAS, JSON1, 1.0f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		String HENSHIN2_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_kurenai_henshin.atlas";
		String JSON2 = "img/char/DCD_Animation/hibiki/hibiki_kurenai_henshin.json";
		new AbstractAnimation("HIBIKI_KURENAI_HENSHIN2", HENSHIN2_ATLAS, JSON2, 1.0f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
			//HibikiAnimationAction2.hibiki_kurenai_normal.getClass();
		this.duration = 2.8F;//倒数时间
		this.startingDuration = 2.8F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		AbstractAnimation HENSHIN;
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 2.5F){
			if(HENSHIN2Start) {
				AbstractAnimation HENSHIN1;
				HENSHIN1 = AbstractAnimation.getAnimation("HIBIKI_KURENAI_HENSHIN1");
				HENSHIN1.setMovable(false);
				HENSHIN1.state.setAnimation(0, "henshin", false);
				CardCrawlGame.sound.playA("hibiki_kurenai_sound", 0F);

				HENSHIN2Start = false;
			}
		}
		if(this.duration < 1.2F){
			if(HENSHIN3Start) {
				HENSHIN =  AbstractAnimation.getAnimation("HIBIKI_KURENAI_HENSHIN2");
				HENSHIN.setMovable(false);
				HENSHIN.state.setAnimation(0, "henshin", false);
				CardCrawlGame.sound.playA("hibiki_kurenai", 0F);
				HENSHIN3Start = false;
			}
		}
		if(this.duration < 0.5F){
			if(HENSHIN4Start) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(55);//切换模型
				HENSHIN4Start = false;
			}
		}
		if(this.duration < 0.0F){
			CardCrawlGame.sound.playA("hibiki_BGM", 0F);
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		AbstractAnimation.clear("HIBIKI_KURENAI_HENSHIN1");
		AbstractAnimation.clear("HIBIKI_KURENAI_HENSHIN2");
		if(HENSHIN1Start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(54);//切换模型
			HENSHIN1Start = false;
		}
	}

	public void dispose() {
	}
}