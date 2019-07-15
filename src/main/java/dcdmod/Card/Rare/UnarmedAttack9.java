package dcdmod.Card.Rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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



public class UnarmedAttack9 extends AbstractCustomCardWithType{
	
	public static final String ID = "UnarmedAttack9";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/UnarmedAttack9.png";
	private static final int COST = 1;
	int s = 0;
	int d = 0;
	
	public UnarmedAttack9() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 0;
		if(upgraded) {
			if(p.hasPower("Strength")) {
				s = p.getPower("Strength").amount;
				s = s * 2;
			}
			if(p.hasPower("Dexterity")) {
				d = p.getPower("Dexterity").amount;
				d = d * 2;
			}
			if((s+d) >= 100) {
				x = m.maxHealth;
			}
			else {
				x = (m.maxHealth * (s+d))/100;
			}
			if(x > m.currentHealth) {
				x = m.currentHealth;
			}
			m.currentHealth -= x;
		}
		else {
			if(p.hasPower("Strength")) {
				s = p.getPower("Strength").amount;
				s = s * 2;
			}
			if(s >= 100) {
				x = m.maxHealth;
			}
			else {
				x = (m.maxHealth * s)/100;
			}
			if(x > m.currentHealth) {
				x = m.currentHealth;
			}
			m.currentHealth -= x;
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, 0, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new UnarmedAttack9();
    }
	
	@Override
	public void optionDecade() {
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}

	@Override
	public void optionKuuga() {
	     setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack9");
        NAME = UnarmedAttack9.cardStrings.NAME;
        DESCRIPTION = UnarmedAttack9.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION =  UnarmedAttack9.cardStrings.UPGRADE_DESCRIPTION;
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
