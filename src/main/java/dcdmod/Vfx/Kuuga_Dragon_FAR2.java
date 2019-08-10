package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_Dragon_FAR2 extends AbstractGameEffect {

	private final int damage;
	private final float x;
	private final float y;
	private AbstractCreature target;
	private int combo;
	private int n = 2;
	private Vector2 start;
	private AbstractPlayer p;

	Kuuga_Dragon_FAR2(AbstractCreature target, int damage, int combo, float x, float y) {
		this.duration = 0.0F;//倒数时间
		this.startingDuration =  0.0F;//持续时间
		this.target = target;
		this.damage = damage;
		this.combo = combo;
		this.x = x;
		this.y = y;
		this.start = new Vector2(target.drawX, target.drawY);
		this.p = AbstractDungeon.player;
	}

	public void update() {

		AbstractAnimation dragon_FAR;
		String DRAGON_FAR_ATLAS;
		String DRAGON_FAR_JSON;
		if(this.combo > 0 && this.duration <= 0.0F){
			if(combo == 4){
				final Decade Decade = (Decade)p;
				Decade.Trickster(110);//切换模型
				AbstractAnimation.clear("dragon_FAR");
				DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.atlas";
				DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.json";
				new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
				dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
				dragon_FAR.setMovable(false);
				dragon_FAR.state.setAnimation(0, "attack", false);
				CardCrawlGame.sound.playA("attack_slash", 0F);
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target ));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
				this.duration += 0.33F;
				combo -=3;
			}
			else if(n == 2){
				final Decade Decade = (Decade)p;
				Decade.Trickster(109);//切换模型
				AbstractAnimation.clear("dragon_FAR");
				DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
				DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
				new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
				dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
				dragon_FAR.setMovable(false);
				dragon_FAR.state.setAnimation(0, "attack", false);
				CardCrawlGame.sound.playA("attack_slash", 0F);
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
				this.duration += 0.33F;
				n --;
			}
			else{
				final Decade Decade = (Decade)p;
				Decade.Trickster(110);//切换模型
				AbstractAnimation.clear("dragon_FAR");
				DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.atlas";
				DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.json";
				new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
				dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
				dragon_FAR.setMovable(false);
				dragon_FAR.state.setAnimation(0, "attack", false);
				CardCrawlGame.sound.playA("attack_slash", 0F);
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target ));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
				this.duration += 0.33F;
				n = 2;
			}
			this.combo --;
		}
		else if(this.combo == 0 && this.duration < 0.0F){
			final Decade Decade = (Decade)p;
			Decade.Trickster(111);//切换模型
			AbstractAnimation.clear("dragon_FAR");
			DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack3.atlas";
			DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack3.json";
			new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
			dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
			dragon_FAR.setMovable(false);
			dragon_FAR.state.setAnimation(0, "attack", false);
			CardCrawlGame.sound.playA("attack_slash", 0F);
			AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
			this.duration += 0.4F;
			this.combo --;
		}
		else if(this.combo == -1 && this.duration < 0.0F){
			final Decade Decade = (Decade)p;
			Decade.Trickster(112);//切换模型
			AbstractAnimation.clear("dragon_FAR");
			DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_finalattack.atlas";
			DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_finalattack.json";
			new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
			dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
			dragon_FAR.setMovable(false);
			dragon_FAR.state.setAnimation(0, "attack", false);
			CardCrawlGame.sound.playA("attack_slash", 0F);
			AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
			this.duration += 0.87F;
			this.combo --;
		}
		else if(this.combo == -2 && this.duration > 0.62F){
			this.target.drawX += 1200 * Settings.scale * Gdx.graphics.getDeltaTime();
		}
		else if(this.combo == -2){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_SpecialPower(this.target),0.0F));
			CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
			this.combo --;
		}
		else if(this.combo == -3 && this.duration < 0.0F){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target , true)));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, p, "KuugaSpecialPower"));

			this.duration += 1.0F;
			this.combo --;
		}
		else if (this.combo == -4 && this.duration < 0.0F){
			final Decade Decade = (Decade)p;
			Decade.Trickster(4);//切换模型
			AbstractAnimation.clear("dragon_FAR");
			p.drawX = this.x;
			p.drawY = this.y;
			this.target.drawX = this.start.x;
			this.isDone = true;
		}

		if(this.duration > 0.0F){
			this.duration -= Gdx.graphics.getDeltaTime();
		}

	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}