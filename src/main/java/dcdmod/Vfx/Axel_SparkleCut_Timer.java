package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Power.XuanyunPower;

import com.badlogic.gdx.graphics.Color;

public class Axel_SparkleCut_Timer extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	private AbstractCreature m;
	private int damage;
	boolean Start= true;
	boolean Final = false;
	int n = 0;

	
	
	public Axel_SparkleCut_Timer(int d) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.m = AbstractDungeon.getMonsters().getRandomMonster(true);
		if(m != null) {
			this.x = m.drawX;
			this.y = m.drawY;
		}
		this.damage = d;
		this.duration = 30.0F;//倒数时间
		this.startingDuration = 30.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!EnterButtonAction.AxelForm) {
			if(Start) {
				for(int i=0;i<EnterButtonAction.EdgePoint;i++) {
					if(n != EnterButtonAction.EdgePoint) {
						n +=1;
					}	
				}
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					 if ((!monster.isDead) && (!monster.isDying)) {
						 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new XuanyunPower(monster), 1));
						 AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_SparkleCut(monster.drawX, monster.drawY), 0F));
					 }
				}
				AbstractDungeon.actionManager.addToTop(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, this.damage), EnterButtonAction.EdgePoint));
				Start = false;
				Final = true;
			}

		}
		if(Final) {
			if(n == EnterButtonAction.EdgePoint&&this.duration<(30.0f-(EnterButtonAction.EdgePoint*2.0f))) {
				this.isDone = true;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;

		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}