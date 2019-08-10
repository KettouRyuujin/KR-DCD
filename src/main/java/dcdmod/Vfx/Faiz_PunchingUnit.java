package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.DCDmod;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Faiz_PunchingUnit extends AbstractGameEffect {

	private int stage = 0;
	private AbstractCreature m;
	private int damage;

	public Faiz_PunchingUnit(float x, float y,AbstractCreature m,int d) {
		this.damage = d;
		this.m = m;
		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间
		if(!DCDmod.AnimationTrigger) {
			String FAIZ_ATLAS = "img/char/DCD_Animation/faiz/faiz_FAR3.atlas";
			String FAIZ_JSON1 = "img/char/DCD_Animation/faiz/faiz_FAR3.json";
			new AbstractAnimation("FAIZ_FAR", FAIZ_ATLAS, FAIZ_JSON1, 0.8f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}
	}

	public void update() {
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			AbstractAnimation FAR1 =  AbstractAnimation.getAnimation("FAIZ_FAR");
			if(FAR1!=null) {
				FAR1.setMovable(false);
			}
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 3.5F && stage == 0) {
				assert FAR1 != null;
				AbstractAnimation.changeAnimation(FAR1, FaizAnimationAction.faiz_punchingunit);
				FAR1.state.setAnimation(0, "punchingunit", false);
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage ++;
			}
			if (this.duration < 3.1F && stage == 1) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage ++;
			}
			if (this.duration < 2.75F && stage== 2) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage++;
			}
			if (this.duration < 2.5F && stage == 3) {
				//蓄力声音
				stage++;
			}
			if (this.duration < 1.8F && stage == 4) {
				AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, AbstractDungeon.player));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
				stage ++;
			}
			if (this.duration < 0.9F && stage == 5) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage ++;
			}
			if (this.duration < 0.3F) {
				this.isDone = true;
				AbstractAnimation.clear("FAIZ_FAR");
				if(Decade.cf != 3) {
					final Decade Decade = (Decade)AbstractDungeon.player;
					Decade.Trickster(34);//恢复站姿
				}
			}
		}
		else {
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 3.5F) {
				for(int i = 0;i<3;i++){
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, AbstractDungeon.player));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				this.isDone = true;
			}
		}
	}

	public void render(SpriteBatch sb) {
	}

	public void dispose() {
	}
}