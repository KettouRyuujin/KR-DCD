package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Decade_defend extends AbstractGameEffect {

	private boolean start = true;

	public Decade_defend() {
		this.duration = 0.35F;//倒数时间
		this.startingDuration = 0.35F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			if(Decade.cf == 81){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.state.setAnimation(0, "defend", true);
			}
			else{
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(81);//切换模型
			}
			start = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}