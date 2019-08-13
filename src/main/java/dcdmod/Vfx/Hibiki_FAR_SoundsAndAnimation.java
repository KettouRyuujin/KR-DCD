package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.DCDmod;
import dcdmod.Helper.SpecialTaikoEffects;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.HibikiTaikoKeyEvent;

public class Hibiki_FAR_SoundsAndAnimation extends AbstractGameEffect {

	private boolean FAR0Start = true;
	private boolean FAR1Start = true;
	private boolean FAR2Start = true;
	private boolean FAR3Start = true;
	private boolean FAR = true;
	private int damage;
	private DamageType damageType;

	public Hibiki_FAR_SoundsAndAnimation(int d,DamageType damageType) {
		this.damage = d;
		this.damageType = damageType;
		this.duration = 5.0F;//倒数时间
		this.startingDuration = 5.0F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 4.5F && FAR){
			CardCrawlGame.sound.playA("FAR_HIBIKI", 0.0f);
			FAR = false;
		}
		if(this.duration < 4.0F){
			if(FAR0Start) {
				
				if(!DCDmod.AnimationTrigger) {
					final Decade Decade = (Decade)AbstractDungeon.player;
					Decade.Trickster(52);//切换模型
					Decade.state.setAnimation(0, "prepare", true);
					String TAIKO_ATLAS2 = "img/char/DCD_Animation/hibiki/taiko_p.atlas";
					String TAIKO_JSON2 = "img/char/DCD_Animation/hibiki/taiko_p_taiko.json";
					new AbstractSummonedAnimation("TAIKO2", TAIKO_ATLAS2, TAIKO_JSON2, 0.8f, AbstractDungeon.player.drawX+50.0f, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
					AbstractSummonedAnimation TAIKO =  AbstractSummonedAnimation.getAnimation("TAIKO2");
					TAIKO.setMovable(false);
			    	TAIKO.state.setAnimation(0, "taiko", true);
				}
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
					}
				}
				FAR0Start = false;
			}
		}
		if(this.duration < 3.0F){
			if(FAR1Start) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
					}
				}
				FAR1Start = false;
			}
		}
		if(this.duration < 2.0F){
			if(FAR2Start) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
					}
				}
				FAR2Start = false;
			}
		}
		if(this.duration < 1.0F){
			if(FAR3Start) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
						AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
					}
				}
				FAR3Start = false;
			}
		}
		if (this.duration < 0.0F) {
			AbstractSummonedAnimation.clear("TAIKO2");
			this.isDone = true;
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
				}
			}
			if(Decade.cf != 3) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(53);//切换模型
			}
			if(!DCDmod.AnimationTrigger && SpecialTaikoEffects.a != 4) {
				SpecialTaikoEffects.a = 3;
				SpecialTaikoEffects.update();
			}
			HibikiTaikoKeyEvent.ComboPoint -=9;
			HibikiTaikoKeyEvent.FeverOut(false);
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}