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

public class Hibiki_attack2 extends AbstractGameEffect {

	private boolean A1 = true;
	private boolean A2 = true;
	private boolean start = true;
	private int damage;
	private DamageType damageType;
	private int magicNumber;
	private AbstractMonster m;

	public Hibiki_attack2(AbstractMonster m,int d,DamageType damageType,int mn) {

		this.damage = d;
		this.m = m;
		this.magicNumber = mn;
		this.damageType = damageType;
		this.duration = 2.0F;//倒数时间
		this.startingDuration = 2.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.9F) {
			if(A1) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					 if ((!monster.isDead) && (!monster.isDying)) {
						 if(monster != m) {
							 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
							 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.magicNumber, AbstractDungeon.player), this.magicNumber)); 
							 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.damage, AbstractDungeon.player), this.damage));
						 }
					 }
				}
				A1=false;
			}
		}
		if (this.duration < 0.2F) {
			if(A2) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new HibikiBurnPower(m, this.magicNumber, AbstractDungeon.player), this.magicNumber)); 
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new HibikiBurnPower(m, this.damage, AbstractDungeon.player), this.damage));
				A2=false;
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
			Decade.Trickster(57);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}