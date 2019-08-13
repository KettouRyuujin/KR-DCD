package dcdmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Patches.ModBaseClassForSLExample;



public class TimeVent extends AbstractCustomCardWithType{
	
	public static final String ID = "TimeVent";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/TimeVent.png";
	private static final int COST = 3;
	public static boolean TimeVentUpgraded = true;
	
	
	public TimeVent() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		setBackgroundTexture("img/512/skill_ODIN.png", "img/1024/skill_ODIN.png");
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		ModBaseClassForSLExample.timevent = true;
		this.moveToDiscardPile();
		if(!upgraded) {
			ModBaseClassForSLExample.TimeVentUpgraded = false;
			TimeVentUpgraded = false;
			for(AbstractCard c : p.masterDeck.group) {
				if(c.cardID.equals(this.cardID) && !c.upgraded) {
					AbstractDungeon.player.masterDeck.removeCard(c);
					break;
				}
			}
		}
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(ModBaseClassForSLExample.timevent) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new TimeVent();
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
            exhaust = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("TimeVent");
        NAME = TimeVent.cardStrings.NAME;
        DESCRIPTION = TimeVent.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = TimeVent.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = TimeVent.cardStrings.EXTENDED_DESCRIPTION;
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
