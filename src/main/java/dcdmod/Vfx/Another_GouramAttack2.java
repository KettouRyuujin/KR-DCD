package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Patches.AbstractAnimation;

/**
 * @author hoykj
 */
public class Another_GouramAttack2 extends AbstractGameEffect {

    private AbstractAnimation kuuga;
    private int stage;
    private AbstractCreature source, target;
    private Vector2 start, current ,Tstart;

    Another_GouramAttack2(AbstractCreature source, AbstractCreature target, float PlayerStartDrawX, float PlayerStartDrawY, float MonsterStartDrawX, float MonsterStartDrawY, AbstractAnimation kuuga) {
        this.duration = 2.3F;
        this.startingDuration = this.duration;
        this.stage = 0;
        this.source = source;
        this.target = target;
        this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2 + target.hb.height / 2);
        this.start = new Vector2(PlayerStartDrawX, PlayerStartDrawY);
        this.Tstart = new Vector2(MonsterStartDrawX, MonsterStartDrawY);
        this.kuuga = kuuga;
    }

    public void update() {
        String ANOTHER_GOURAM_ATTACK_ATLAS;
        String ANOTHER_GOURAM_ATTACK_JSON;

        if(this.duration == this.startingDuration){
            AbstractAnimation.clear("another_gouram_attack");
            ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram2.atlas";
            ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram2.json";
            new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8f, kuuga.drawX, kuuga.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
            kuuga = AbstractAnimation.getAnimation("another_gouram_attack");
            kuuga.setMovable(false);
            kuuga.state.setAnimation(0, "gouram", false);
        }

        this.duration -= Gdx.graphics.getDeltaTime();


        if((this.duration < this.startingDuration - 0.2F) && (this.duration > this.startingDuration - 0.5F)){
            kuuga.drawY += 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.y += 3000 * Settings.scale * Gdx.graphics.getDeltaTime();
            kuuga.drawX -= 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
            this.current.x -= 2000 * Settings.scale * Gdx.graphics.getDeltaTime();
        }

        if(this.duration < this.startingDuration - 0.5F && stage == 0){
            if(kuuga.drawY > this.start.y){
                kuuga.drawY += (this.target.hb.cY - this.current.y) / 0.3 * Gdx.graphics.getDeltaTime();
                kuuga.drawX -= (this.target.hb.cX - this.current.x) / 1.0 * Gdx.graphics.getDeltaTime();
            }
            else{
                stage ++;
            }
        }
        else if(this.stage == 1){
            AbstractAnimation.clear("another_gouram_attack");
            ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram3.atlas";
            ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram3.json";
            new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8f, kuuga.drawX, kuuga.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
            kuuga = AbstractAnimation.getAnimation("another_gouram_attack");
            kuuga.setMovable(false);
            kuuga.state.setAnimation(0, "gouram", false);
            stage ++;
        }

        if(this.duration < 0.0F){
            AbstractAnimation.clear("another_gouram_attack");
            this.target.drawX = this.Tstart.x;
            this.target.drawY = this.Tstart.y;
            this.isDone = true;
        }



    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}