package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Patches.AbstractSummonedAnimation;

public class Kabuto_henshin_SoundsAndAnimation extends AbstractGameEffect {

	private boolean HENSHIN1Start = true;
	private boolean HENSHIN2Start = true;
	private boolean HENSHIN3Start = true;
	private boolean start = true;


	public Kabuto_henshin_SoundsAndAnimation(float x, float y) {

		String HENSHIN1_ATLAS = "img/char/DCD_Animation/kabuto/kabuto_henshin_card.atlas";
		String JSON1 = "img/char/DCD_Animation/kabuto/kabuto_henshin_card.json";
		new AbstractSummonedAnimation("KABUTO_HENSHIN1", HENSHIN1_ATLAS, JSON1, 1.0f, x , y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		String HENSHIN2_ATLAS = "img/char/DCD_Animation/kabuto/kabuto_henshin_P.atlas";
		String JSON2 = "img/char/DCD_Animation/kabuto/kabuto_henshin_P.json";
		new AbstractAnimation("KABUTO_HENSHIN2", HENSHIN2_ATLAS, JSON2, 1.0f, x+5.0f , y+25.0f, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);

		this.duration = 6.33F;//倒数时间
		this.startingDuration = 6.33F;//持续时间

	}

	public void update() {
		AbstractAnimation HENSHIN;
		AbstractSummonedAnimation HENSHIN1;
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 6.0F){
			if(HENSHIN1Start) {
				HENSHIN1 = AbstractSummonedAnimation.getAnimation("KABUTO_HENSHIN1");
				HENSHIN1.setMovable(false);
				HENSHIN1.state.setAnimation(0, "card", false);
				CardCrawlGame.sound.playA("kamenride", 0.0f);
				CardCrawlGame.sound.playA("kabuto_henshin", 0F);
				HENSHIN1Start = false;
			}
		}
		if(this.duration < 5.08F){
			if(HENSHIN2Start) {
				HENSHIN =  AbstractAnimation.getAnimation("KABUTO_HENSHIN2");
				HENSHIN.setMovable(false);
				HENSHIN.state.setAnimation(0, "P", false);
				HENSHIN2Start = false;
			}
		}
		if(this.duration < 4.7F){
			if(HENSHIN3Start) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(59);//切换模型
				HENSHIN3Start = false;
			}
		}
		if(this.duration < 0.7F){
			AbstractAnimation.clear("KABUTO_HENSHIN1");
			AbstractAnimation.clear("KABUTO_HENSHIN2");
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(60);//切换模型
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(43);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}