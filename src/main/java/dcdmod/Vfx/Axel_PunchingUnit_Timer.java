package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Patches.AbstractAnimation;
import com.badlogic.gdx.graphics.Color;

public class Axel_PunchingUnit_Timer extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	private AbstractCreature m;
	private int damage;
	boolean Start= true;
	boolean Final = false;
	int n = 0;
	AbstractAnimation AXEL4 = null;
    public static String FAIZ_ATLAS = "img/char/DCD_Animational/faiz_Axel/Axel_PunchingUnit.atlas";
    public static String FAIZ_JSON1 = "img/char/DCD_Animational/faiz_Axel/Axel_PunchingUnit.json";
	
	
	public Axel_PunchingUnit_Timer(int d) {
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
				AXEL4 = new AbstractAnimation(FAIZ_ATLAS,FAIZ_JSON1, 0.8f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f , 1.0f);
				if(AXEL4 != null) {
					AXEL4.setMovable(false);
				}
				AbstractAnimation.changeAnimation(AXEL4, FaizAnimationAction.axel_FAR_P);
				AXEL4.state.setAnimation(0, "PunchingUnit", true);
				for(int i=0;i<EnterButtonAction.ShotPoint;i++) {
					if(n != EnterButtonAction.ShotPoint) {
						n +=1;
					}	
				}
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					 if ((!monster.isDead) && (!monster.isDying)) {
					 AbstractDungeon.actionManager.addToTop(new RemoveAllBlockAction(monster, AbstractDungeon.player));
					 }
				}
				AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, this.damage), EnterButtonAction.ShotPoint));
				Start = false;
				Final = true;
			}

		}
		if(Final) {
			if(n == EnterButtonAction.ShotPoint&&this.duration<(30.0f-(EnterButtonAction.ShotPoint*2.0f))) {
				AXEL4.state.setAnimation(0, "PunchingUnit", false);
				EnterButtonAction.ShotPoint = 0;
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