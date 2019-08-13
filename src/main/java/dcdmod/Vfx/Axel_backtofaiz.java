package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Axel_backtofaiz extends AbstractGameEffect {

	private boolean start = true;
	
	public Axel_backtofaiz() {

		this.duration = 2.5F;//倒数时间
		this.startingDuration = 2.5F;//持续时间

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
			Decade.Trickster(41);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}