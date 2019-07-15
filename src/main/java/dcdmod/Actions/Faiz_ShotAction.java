package dcdmod.Actions;

	import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
	import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
	import com.megacrit.cardcrawl.core.AbstractCreature;
	import com.megacrit.cardcrawl.cards.DamageInfo;
	import com.megacrit.cardcrawl.actions.AbstractGameAction;

	public class Faiz_ShotAction extends AbstractGameAction {
		private DamageInfo info;
		private static final float DURATION = 0.1f;

		public Faiz_ShotAction(final AbstractCreature target, final DamageInfo info) {
			this.setValues(target, this.info = info);
			this.actionType = AbstractGameAction.ActionType.DAMAGE;
			this.duration = DURATION;
		}

		public void update() {
			if (this.duration == 0.1f && this.target != null) {
				AbstractDungeon.effectList.add(
						new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				this.target.damage(this.info);
				if ((this.target.currentBlock == 0 || this.target.currentBlock < 0) && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
					FaizGearAction.FaizPoint += 1;
				}
				if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
					AbstractDungeon.actionManager.clearPostCombatActions();
				}
			}
			this.tickDuration();
		}
	}