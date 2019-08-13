package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Ryuki_guardattack extends AbstractGameEffect {

	private boolean start = true;

	public Ryuki_guardattack() {

		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(30);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(26);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}