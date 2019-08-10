package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_DragonAttack extends AbstractGameEffect {

	private final int damage;
	private final boolean isFAR;
	private final float x;
	private final float y;
	private AbstractCreature target;
	private int combo;
	private int n = 2;
	private AbstractPlayer p;

	public Kuuga_DragonAttack(AbstractCreature target, int damage, int combo, boolean isFAR, float x, float y) {
		this.duration = 0.0F;//倒数时间
		this.startingDuration =  0.0F;//持续时间
		this.target = target;
		this.combo = combo;
		this.damage = damage;
		this.isFAR = isFAR;
		this.x = x;
		this.y = y;
		this.p = AbstractDungeon.player;
	}

	public void update() {

		AbstractAnimation dragon_FAR;
		String DRAGON_FAR_ATLAS;
		String DRAGON_FAR_JSON;
		if(this.combo == 1 && isFAR){
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, p, "KuugaSpecialPower"));
		}
		if(this.combo > 0 && this.duration <= 0.0F){
			if(n == 2){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(109);//切换模型
				if(isFAR){
					AbstractAnimation.clear("dragon_FAR");
					DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
					DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
					new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
					dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
					dragon_FAR.setMovable(false);
					dragon_FAR.state.setAnimation(0, "attack", false);
				}
				CardCrawlGame.sound.playA("attack_slash", 0F);
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL)));
				this.duration += 0.33F;
				n --;
			}
			else{
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(110);//切换模型
				if(isFAR){
					AbstractAnimation.clear("dragon_FAR");
					DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
					DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
					new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8f, p.drawX, p.drawY, p.hb_w, p.hb_h, 1.0f);
					dragon_FAR = AbstractAnimation.getAnimation("dragon_FAR");
					dragon_FAR.setMovable(false);
					dragon_FAR.state.setAnimation(0, "attack", false);
				}
				CardCrawlGame.sound.playA("attack_slash", 0F);
				AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(target,new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL)));
				this.duration += 0.33F;
				n = 2;
			}
			this.combo --;
		}
		else if(this.combo == 0 && this.duration < 0.0F){
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//切换模型
			if(isFAR){
				AbstractDungeon.player.drawX = this.x;
				AbstractDungeon.player.drawY = this.y;
			}
			this.isDone = true;
		}

		if(this.duration > 0.0F){
			this.duration -= Gdx.graphics.getDeltaTime();
		}

	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}