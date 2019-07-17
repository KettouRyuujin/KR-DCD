package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.DCDmod;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Power.XuanyunPower;

import com.badlogic.gdx.graphics.Color;

public class Faiz_SparkleCut extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	boolean One = true;
	boolean Two = true;
	boolean Three = true;
	boolean Four = true;
	boolean Start = true;
	boolean Sound = true;
	private AbstractCreature m;
	private int damage;
    public static String FAIZ_ATLAS = "img/char/DCD_Animation/faiz/faiz_FAR3.atlas";
    public static String FAIZ_JSON1 = "img/char/DCD_Animation/faiz/faiz_FAR3.json";
    
	public Faiz_SparkleCut(float x, float y,AbstractCreature m,int d) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.damage = d;
		this.m = m;
		this.x = x;
		this.y = y;
		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间
		this.color = Color.WHITE.cpy();
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			new AbstractAnimation("FAIZ_FAR",FAIZ_ATLAS,FAIZ_JSON1, 0.8f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}
	}

	public void update() {
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			AbstractAnimation FAR1 =  AbstractAnimation.getAnimation("FAIZ_FAR");
			if(FAR1!=null) {
				FAR1.setMovable(false);
			}
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 3.5F) {
				if(Start) {
					AbstractAnimation.changeAnimation(FAR1, FaizAnimationAction.faiz_sparklecut);
					FAR1.state.setAnimation(0, "sparklecut", false);
		        	Start = false;
				}
			}
			if (this.duration < 2.9F) {
				if(Sound) {
					CardCrawlGame.sound.playA("faiz_sword", 0F);
					Sound = false;
				}
			}
			if (this.duration < 2.75F) {
				if(One) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new XuanyunPower(m), 1));
		        	One = false;
				}
			}
			if (this.duration < 2.4F) {
				if(Two) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
		        	Two = false;
				}
			}
			if (this.duration < 2.3F) {
				if(Three) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
					Three = false;
				}
			}
			if (this.duration < 1.9F) {
				if(Four) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));	
					Four = false;
				}
			}
			if (this.duration < 1.2F) {
				this.isDone = true;
				if(Decade.cf != 3) {
					final Decade Decade = (Decade)AbstractDungeon.player;
					Decade.Trickster(34);//恢复站姿
				}
			}
		}
		else {
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 2.9F) {
				if(Sound) {
					CardCrawlGame.sound.playA("faiz_sword", 0F);
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new XuanyunPower(m), 1));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
					this.isDone = true;
					Sound = false;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}