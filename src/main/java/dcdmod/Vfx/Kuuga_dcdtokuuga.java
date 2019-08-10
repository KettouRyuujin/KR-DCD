package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_dcdtokuuga extends AbstractGameEffect {
	private boolean start = true;
	private int stage = 0;

	public Kuuga_dcdtokuuga() {
		this.duration = 2.83F;//倒数时间
		this.startingDuration = 2.83F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < this.startingDuration - 2.0F && stage == 0) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(85);//时间结束后切换回原本模型
			stage ++;
		}
		if(this.duration < 0.0F){
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//时间结束后切换回原本模型
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(5);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}