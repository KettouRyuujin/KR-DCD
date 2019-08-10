package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.Kuuga_FAR_Background;
import dcdmod.Vfx.Kuuga_FAR_SoundsAndAnimation;
import dcdmod.Vfx.Kuuga_Pegasus_FAR;
import dcdmod.Vfx.Kuuga_Pegasus_FAR2;


public class PegasusAttack extends AbstractCustomCardWithType{
	
	public static final String ID = "PegasusAttack";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FinalAttackRide_Kuuga.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 11;

	
	
	public PegasusAttack() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		exhaust = true;
		setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("FAR", 0F);
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_SoundsAndAnimation(p.drawX, p.drawY), 3.2F));
		if(!DCDmod.AnimationTrigger){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Pegasus_FAR2(p,m,this.damage)));
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(false,false)));
		}
		else{
			for(int i = 0;i < 3; i++) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					if ((!monster.isDead) && (!monster.isDying)) {
						AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
					}
				}
			}
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new PegasusAttack();
    }
	
	@Override
	public void optionDecade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKuuga() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionAgito() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionRyuki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionFaiz() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionBlade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionHibiki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKabuto() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionDenO() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKiva() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionNeutral() {

	}
    
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
		}
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PegasusAttack");
        NAME = PegasusAttack.cardStrings.NAME;
        DESCRIPTION = PegasusAttack.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = PegasusAttack.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.FAR[0], DCDmod.FAR[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.FAR[6], DCDmod.FAR[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
		}
	}

	
	
}
