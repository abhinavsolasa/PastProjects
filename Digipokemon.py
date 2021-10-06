import random
class Pokemon():
    
    def __init__(self, name):
        self.name = name
        self.health = 100
        self.hunger = 50
        self.wins = 0
        self.losses = 0
        self.round = 0


    def battle(self):
        res = random.randint(1,2)
        self.round += 1
        if res == 1:
            print(self.name + " has won the battle! ")
            self.wins += 1
            self.health += 10
            self.hunger -= 10

        elif res == 2:
            print(self.name + " has lost the battle! ")
            self.health -= 10
            self.losses += 1
            self.hunger -= 10
            
    def life(self):
        if self.health > 100:
            self.health=100
        if self.health <= 0:
            print(self.name + " is Dead! \n")
        if self.wins == 10:
            print("10 Wins! Your digimon can retire\n")
        if self.losses == 10:
            print("10 Losses! Tuff. Your digimon died from losing too much\n")
        if self.hunger <= 0:
            print(self.name + " died of hunger\n")
        if self.hunger >= 100:
            print(self.name + " died of overeating \n")
        if self.round >= 6:
            print(self.name + " lost 10 health points for not sleeping\n")
            self.health-=10
   
        if(self.hunger <= 0 or self.hunger >= 100 or self.wins == 10 or self.losses == 10 or self.health <= 0):
            return(0)
        else:
            return(1)

    def eat(self):
        self.round += 1
        food = random.randint(1,3)
        if food == 1:
            print(self.name + " ate some Yummy food\n")
            self.health +=10
            self.hunger +=10
            
        elif food == 2:
            print(self.name + " ate rations\n")
            self.hunger +=15
            
        elif food == 3:
            print("Yuck, you ate some rotten food\n")
            self.health-=10
            
    def status(self):
        print("\nHealth: " + str(self.health))
        print("\nHunger: " + str(self.hunger))
        print("\nWins: " + str(self.wins))
        print("\nLosses: " + str(self.losses))


    def sleep(self):
        self.health += 10
        self.round=0
        self.hunger -= 10
        print(self.name + " has slept! \n")


name = input("What do you want your pokemon to be called?")

pokemon = Pokemon(name)

game_over = False
sleep_check = 0

while game_over == False :
    print("\n1. Battle\n2. Eat\n3. sleep\n4. Check Status")
    option = int(input("\nChoose the numbered option you want: "))
    if option == 1:
        pokemon.battle()
    elif option == 2:
        pokemon.eat()
    elif option == 3:
        pokemon.sleep()
    elif option == 4:
        pokemon.status()
    else:
        print("\nWhat are you Doing?!!!")
    if pokemon.life() == 0:
        game_over = True
