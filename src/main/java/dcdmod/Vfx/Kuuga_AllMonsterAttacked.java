package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_AllMonsterAttacked extends AbstractGameEffect {

	private String id;
	private boolean start = true;
	public static int x;

	public Kuuga_AllMonsterAttacked() {
		this.duration = 0.58F;//倒数时间
		this.startingDuration = 0.58F;//持续时间
		this.id = "kuuga_attacked" + x;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			x += 1;
			String KUUGA_ATTACKED_ATLAS;
			String KUUGA_ATTACKED_JSON;
			if(AbstractDungeon.player.hasPower("KuugaDragonPower")||AbstractDungeon.player.hasPower("RisingDragonPower")){
				KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/dragon/kuuga_monsterattacked2.atlas";
				KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/dragon/kuuga_monsterattacked2.json";
			}
			else if(AbstractDungeon.player.hasPower("KuugaTitanPower")||AbstractDungeon.player.hasPower("RisingTitanPower")){
				KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/kuuga_monsterattacked3.atlas";
				KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/kuuga_monsterattacked3.json";
			}
			else if(AbstractDungeon.player.hasPower("KuugaPegasusPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")){
				KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/kuuga_monsterattacked4.atlas";
				KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/kuuga_monsterattacked4.json";
			}
			else{
				KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/kuuga_monsterattacked.atlas";
				KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/kuuga_monsterattacked.json";
			}
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
					x += 1;
					this.id = "kuuga_attacked" + x;
					new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8f, monster.drawX, monster.drawY + monster.hb_h/2, monster.hb_w, monster.hb_h, 1.0f);
					AbstractAnimation kuuga_attacked =  AbstractAnimation.getAnimation(this.id);
					kuuga_attacked.setMovable(false);
					kuuga_attacked.state.setAnimation(0, "attacked", false);
					CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_AllMonsterAttacked_End(this.id)));
				}
			}

			start = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}