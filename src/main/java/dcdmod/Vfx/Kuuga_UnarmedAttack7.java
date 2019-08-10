package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_UnarmedAttack7 extends AbstractGameEffect {

	private boolean start = true;
	private AbstractCreature target;

	public Kuuga_UnarmedAttack7(AbstractCreature target) {
		this.duration = 0.6F;//倒数时间
		this.startingDuration = 0.6F;//持续时间
		this.target = target;
	}

	public void update() {
		if(this.duration == this.startingDuration){
			AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
		}

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(95);//切换模型
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