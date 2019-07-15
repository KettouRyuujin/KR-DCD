package dcdmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class NMDAZYYGL extends AbstractCustomCardWithType{
	
	public static final String ID = "NMDAZYYGL";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/NMDAZYYGL.gif";
	private static final int COST = 1;
	
	
	public NMDAZYYGL() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		m.escape();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(m != null && m.type == AbstractMonster.EnemyType.BOSS) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		if(m != null && m.id == "Darkling") {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[1];
		}
		return canUse;
	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new NMDAZYYGL();
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
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("NMDAZYYGL");
        NAME = NMDAZYYGL.cardStrings.NAME;
        DESCRIPTION = NMDAZYYGL.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NMDAZYYGL.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
		}
	}

	
	
}
