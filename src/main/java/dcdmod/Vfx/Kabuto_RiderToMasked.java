package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kabuto_RiderToMasked extends AbstractGameEffect {

	private boolean start = true;

	public Kabuto_RiderToMasked() {

		this.duration = 1.2F;//倒数时间
		this.startingDuration = 1.2F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(61);//切换模型
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			CardCrawlGame.sound.playA("formride", 0.0f);
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(62);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}