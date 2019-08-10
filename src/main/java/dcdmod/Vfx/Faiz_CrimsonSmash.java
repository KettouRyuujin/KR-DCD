package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class Faiz_CrimsonSmash extends AbstractGameEffect {


	private int stage = 0;
	private AbstractCreature m;
	private int damage;

	public Faiz_CrimsonSmash(float x, float y,AbstractCreature m,int d) {
		this.damage = d;
		this.m = m;
		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
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
				AbstractAnimation.changeAnimation(FAR1, FaizAnimationAction.faiz_FAR2);
				FAR1.state.setAnimation(0, "FAR2", false);
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage ++;
			}
			if (this.duration < 3.0F && stage == 1) {
				AbstractAnimation.changeAnimation(FAR1, FaizAnimationAction.faiz_FAR);
				FAR1.state.setAnimation(0, "FAR3", false);
				stage ++;
			}
			if (this.duration < 2.5F && stage == 2) {
				for(int i=0;i<5;i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				stage ++;
			}
			if (this.duration < 1.5F && stage == 3) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				stage ++;
			}
			if (this.duration < 1.0F && stage == 4) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

				stage ++;
			}
			if (this.duration < 0.0F) {
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
			if (this.duration < 3.5F && stage == 0) {
				for(int i=0;i<5;i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				for(int i=0;i<3;i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
				stage ++;
				this.isDone = true;
			}
		}
	}

	public void render(SpriteBatch sb) {
	}

	public void dispose() {
	}
}