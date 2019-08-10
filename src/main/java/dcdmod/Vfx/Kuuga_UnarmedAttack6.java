package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_UnarmedAttack6 extends AbstractGameEffect {

	private final int damage;
	private final DamageInfo.DamageType damageType;
	private int stage;
	private AbstractCreature target;

	public Kuuga_UnarmedAttack6(AbstractCreature target, int damage, DamageInfo.DamageType DamageType) {
		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间
		this.target = target;
		this.damage = damage;
		this.damageType = DamageType;
	}

	public void update() {

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && stage == 0) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(94);//切换模型
			stage ++;
		}
		else if(this.duration < 0.6F && stage == 1) {
			AbstractDungeon.actionManager.addToTop(new DamageAction(target,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType)));
			AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
			stage++;
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