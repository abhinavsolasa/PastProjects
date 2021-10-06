# Place bets-> 3 spots with same money on each
# Deal 3 cards to each player and 2 cards to dealer face down
# Pull bet/ keep it
    #If pull back, remove one spot of the bet
    #If bet is kept, continue to next player
# Dealer flips first card
    #If pull back, remove one spot of the bet
    #If bet is kept, continue to next player
#Dealer flips second card
    #Check poker hands for each player

from lirclasses import *

class LetitRide(object):
    def __init__(self):
        self.num_players = int(input("How many players? "))

        # Set up players
        self.players = []
        for i in range(self.num_players):
            f = i+1
            self.players.append(Player("Player " + str(f)))

        #Set up dealer
        self.dealer = lirDealer("Dealer")
        dealer_hand = []
        dealer_hand.append(self.dealer.hand)

        #set up deck            
        self.deck = Deck()
        self.deck.populate()
        self.deck.shuffle()
        
        
        player_hands = []
        for player in self.players:
            player_hands.append(player.hand)

        self.deck.deal(dealer_hand, per_hand = 2)
        self.deck.deal(player_hands, per_hand = 3)
        count = 0
        for player in self.players:
            player.hand.clear()
            if count ==0:
                player.hand.cards.append(Card("3", "d"))
                player.hand.cards.append(Card("4", "d"))
                player.hand.cards.append(Card("5", "d"))
            elif count == 1:
                player.hand.cards.append(Card("5", "c"))
                player.hand.cards.append(Card("10", "c"))
                player.hand.cards.append(Card("Q", "c"))
            count += 1
        
        #set dealer cards to face down
        for card in self.dealer.hand.cards:
            card.is_face_up = False
        num = 1
            
        for player in self.players:
            for card in player.hand.cards:
                card.is_face_up = True
            mon = int(input("Place bet per circle for Player " + str(num) + " "))
            ans = input("Do you want a bonus bet? \n")
            if ans == 'y':
                player.bonus = True
            for i in range(0,3):
                player.money.append(mon)
            num += 1

        # Printing out the numbers for everyone
        
        print(self.dealer)
        for card in self.dealer.hand.cards:
            print(str(card))
        
        for player in self.players:
            print(player)
            print(player.money)
            for card in player.hand.cards:
                print(str(card))
            print("\n")
            
    def play(self):
        # Ask for pulling back or keeping bets before flipping first card
        for player in self.players:
            ans = input("Does " + str(player) + "want to pull back or keep the bet? ")
            if ans == 'pull':
                player.money.pop()
            print(player.money)

        #flip first dealer card
        self.dealer.hand.cards[0].is_face_up = True

        print(self.dealer)
        for card in self.dealer.hand.cards:
            print(str(card))
        
        for player in self.players:
            print(player)
            print(player.money)
            for card in player.hand.cards:
                print(str(card))
            print("\n")
        
        # Ask for pulling back or keeping bets before flipping first card
        for player in self.players:
            ans = input("Does " + str(player) + "want to pull back or keep the bet? ")
            if ans == 'pull':
                player.money.pop()
            print(player.money)

        # Flip second dealer card
        self.dealer.hand.cards[1].is_face_up = True

        print(self.dealer)
        for card in self.dealer.hand.cards:
            print(str(card))

        print("Dealer and Player decks combined: \n")
        # Combining player deck and dealer deck
        for player in self.players:
            player.hand.cards.extend((self.dealer.hand.cards))

        for player in self.players:
            player.hand.sort()
            print(player)
            print(player.money)
            for card in player.hand.cards:
                print(str(card))
            print("\n")
            
        for player in self.players:
            print(player)
            """
            print("Royal Flush: " + str(player.hand.royalflush()) + "\n")
            print("Straight Flush: " + str(player.hand.straightflush()) + "\n")
            print("Four of a kind: " + str(player.hand.fourofakind()) + "\n")
            print("Full House: " + str(player.hand.fullhouse()) + "\n")
            print("Flush: " + str(player.hand.flush()) + "\n")
            print("Straight: " + str(player.hand.straight()) + "\n")
            print("Three of a kind: " + str(player.hand.triple()) + "\n") 
            print("Double: " + str(player.hand.checkpair()) + "\n")
            """
            add = 0
            total = 0
            for money in player.money:
                add += money
                total = add
            
            if player.hand.royalflush() == True:
                total *= 1000
                if player.bonus == True:
                    print("You won a bonus of $20,000!\n")
                    total += 20000
                    total -=2
                player.totalmoney = total + add
                print("Royal flush. Your total money: " + str(player.totalmoney))

            elif player.hand.straightflush() == True:
                total *= 200
                if player.bonus == True:
                    print("You won a bonus of $1,000!\n")
                    total += 1000
                    total -=2
                player.totalmoney = total + add
                print("Straight flush. Your total money: " + str(player.totalmoney))
                      
            elif player.hand.fourofakind() == True:
                total *= 50
                if player.bonus == True:
                    print("You won a bonus of $100!\n")
                    total += 100
                    total -=2
                player.totalmoney = total + add
                print("Four of a kind. Your total money: " + str(player.totalmoney))
                      
            elif player.hand.fullhouse() == True:
                total *= 11
                if player.bonus == True:
                    print("You won a bonus of $75!\n")
                    total += 75
                    total -=2
                player.totalmoney = total + add
                print("Full house. Your total money: " + str(player.totalmoney))
                      
            elif player.hand.flush() == True:
                total *= 8
                if player.bonus == True:
                    print("You won a bonus of $50!\n")
                    total += 50
                    total -=2
                player.totalmoney = total + add
                print("Flush. Your total money: " + str(player.totalmoney))
                      
            elif player.hand.straight() != 0:
                total *= 5
                if player.bonus == True:
                    print("You won a bonus of $25!\n")
                    total += 25
                    total -=2
                player.totalmoney = total + add
                print("Straight. Your total money: " + str(player.totalmoney))
            elif player.hand.triple() != 0:
                total *= 3
                if player.bonus == True:
                    print("You won a bonus of $4!\n")
                    total += 4
                    total -=2
                player.totalmoney = total + add
                print("Three of a kind. Your total money: " + str(player.totalmoney))
            elif player.hand.checkpair() != 0:
                total *= 1
                if player.bonus == True:
                    print("You won a bonus of $1!\n")
                    total += 1
                    total -=2
                    
                player.totalmoney = total + add 
                print("10 pairs or better. Your total money: " + str(player.totalmoney))
            
            else:
                print("No poker hands. You lost your money!\n")
                player.totalmoney = 0
            
            print("\nTotal money: " + str(player.totalmoney))
                  

lir = LetitRide()
lir.play()
            
                                
            
                
        
                     
        

