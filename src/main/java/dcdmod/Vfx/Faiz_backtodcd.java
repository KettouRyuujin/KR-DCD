package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Faiz_backtodcd extends AbstractGameEffect {

	private boolean faizStart = true;
	private boolean faizEnd = true;
	
	public Faiz_backtodcd() {
		this.duration = 2.0F;//倒数时间
		this.startingDuration = 2.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.5F) {
			if(faizEnd) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(10);//中途切换模型
				faizEnd = false;
			}
		}
		if (this.duration < 0.4F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {
		if(faizStart) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(37);//切换模型
			faizStart = false;
		}
	}

	public void dispose() {
	}
}