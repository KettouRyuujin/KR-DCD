package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_TitanDefend extends AbstractGameEffect {

	private boolean start = true;

	public Kuuga_TitanDefend() {
		this.duration = 0.76F;//倒数时间
		this.startingDuration = 0.76F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(Decade.cf == 114){
			this.isDone = true;
		}
		else if(this.duration < this.startingDuration && start) {
			if(Decade.cf == 115){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.state.setAnimation(0,"defend",true);
			}
			else{
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(115);//切换模型
			}

			start = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}