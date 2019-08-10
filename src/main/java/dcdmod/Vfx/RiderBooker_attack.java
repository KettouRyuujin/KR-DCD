package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class RiderBooker_attack extends AbstractGameEffect {

	private final int damage;
	private final DamageInfo.DamageType Type;
	private final AbstractMonster m;
	private boolean start = true;
	private boolean attack = true;

	public RiderBooker_attack(AbstractMonster m,int x, DamageInfo.DamageType Type) {
		this.damage = x;
		this.Type = Type;
		this.m = m;
		this.duration = 0.92F;//倒数时间
		this.startingDuration = 0.92F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(78);//切换模型
			start = false;
		}


		if(this.duration < 0.2 && attack){
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, this.Type), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			attack = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}