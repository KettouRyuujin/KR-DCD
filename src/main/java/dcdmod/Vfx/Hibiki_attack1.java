package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Power.HibikiBurnPower;

public class Hibiki_attack1 extends AbstractGameEffect {

	private boolean A1 = true;
	private boolean start = true;
	private int damage;
	private DamageType damageType;
	private int magicNumber;

	public Hibiki_attack1(int d,DamageType damageType,int mn) {

		this.damage = d;
		this.magicNumber = mn;
		this.damageType = damageType;
		this.duration = 1.6F;//倒数时间
		this.startingDuration = 1.6F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.6F) {
			if(A1) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.magicNumber, AbstractDungeon.player), this.magicNumber));
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.damage, AbstractDungeon.player), this.damage));
					}
				}
				A1=false;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			if(Decade.cf != 3) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(55);//时间结束后切换回原本模型
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(56);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}