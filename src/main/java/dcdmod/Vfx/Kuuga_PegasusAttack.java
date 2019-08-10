package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Power.KuugaSpecialPower;

public class Kuuga_PegasusAttack extends AbstractGameEffect {

	private final int damage;
	private int stage;

	public Kuuga_PegasusAttack( int damage) {
		this.duration = 0.6F;//倒数时间
		this.startingDuration = 0.6F;//持续时间
		this.damage = damage;
	}

	public void update() {

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && stage == 0) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(132);//切换模型
			AbstractDungeon.effectsQueue.add(new Kuuga_AllMonsterAttacked());
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player,this.damage, DamageInfo.DamageType.HP_LOSS)));
					if(AbstractDungeon.player.hasPower("RisingMightyPower")){
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,AbstractDungeon.player, new KuugaSpecialPower(monster,1), 1));
					}
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,AbstractDungeon.player, new KuugaSpecialPower(monster,1), 1));
				}
			}
			stage ++;
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