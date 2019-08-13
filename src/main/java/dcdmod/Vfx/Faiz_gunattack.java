package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Faiz_gunattack extends AbstractGameEffect {

	private boolean start = true;

	public Faiz_gunattack() {

		this.duration = 1.2F;//倒数时间
		this.startingDuration = 1.2F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			if(Decade.cf != 3) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(34);//时间结束后切换回原本模型
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(38);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}