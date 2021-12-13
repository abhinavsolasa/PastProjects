
import csv
import pygal
import pandas as pd
import numpy as np
from datetime import datetime
from collections import defaultdict
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score
import random
#from sklearn.cross_validation import train_test_split


#filename = "E:/Python Resources/attempt 2/KNN project resources/Housing data.csv"

filename = 'Housing data.csv'
with open(filename) as f: 
    reader = csv.reader(f) # read rows into a dictionary format
    header_row = next(reader)
    rows = []
    prices= []
    areas= []
    bedrooms= []
    garages= []
    bathrooms= []
    lotsizes = []
    elemscores = []
    names = []
    avgareas = []
    for row in reader: 
        rows.append(row)
        name = row[0]
        price = float(row[3])
        area = float(row[4])
        bedroom = float(row[5])
        bathroom = float(row[6])
        garage= float(row[7])
        lotsize = float(row[8])
        elemscore = row[9]
        avgarea = float(row[10])
        
        areas.append(area)
        prices.append(price)
        bedrooms.append(bedroom)
        bathrooms.append(bathroom)
        garages.append(garage)
        lotsizes.append(lotsize)
        elemscores.append(elemscore)
        names.append(name)
        avgareas.append(avgarea)
       
"""
print("Receiving Data")
print(areas)
"""

def normalize():
    minprice = min(prices)
    maxprice = max(prices)
    minarea = min(areas)
    maxarea = max(areas)
    minbath = min(bathrooms)
    maxbath = max(bathrooms)
    minbed = min(bedrooms)
    maxbed = max(bedrooms)
    minlotsize = min(lotsizes)
    maxlotsize = max(lotsizes)
    minelem = min(elemscores)
    maxelem = max(elemscores)
    mingarage = min(garages)
    maxgarage = max(garages)       
            
    for f in range(0,len(areas)):
        prices[f] = float(prices[f]- minprice)/(maxprice-minprice)
        areas[f] = float(areas[f]- minarea)/(maxarea-minarea)
        bathrooms[f] = float(bathrooms[f]- minbath)/(maxbath-minbath)
        bedrooms[f] = float(bedrooms[f]- minbed)/(maxbed-minbed)
        lotsizes[f] = float(lotsizes[f]- minlotsize)/(maxlotsize-minlotsize)
        garages[f] = float(garages[f]- mingarage)/(maxgarage-mingarage)
        #elemscores[f] = float(elemscores[f]- minelem)/(maxelem-minelem)
    """
    print("Normalized data")
    print(garages)
    print(bedrooms)
    print(bathrooms)
    print(areas)
    print(prices)
    print("finished normalizing")
    """   
    
def createdata(X_train, y_train, X_test, y_test):
    trainrand = []
    testrand = []
    numpyrow = 0
    while len(trainrand) < 19:
        randint = random.randint(0, 26)
        print()
        print()
        print("Train rand size: " + str(len(trainrand)))
        
        if randint not in trainrand:
            trainrand.append(randint)
            print("random train: " + str(randint))
            start = randint * 10
            for k in range (start, start + 10):
                #data = []
                print("row position: " + str(start))
                X_train[numpyrow][0] = prices[k]
                X_train[numpyrow][1] = areas[k]
                X_train[numpyrow][2] = bathrooms[k]
                X_train[numpyrow][3] = bedrooms[k]
                X_train[numpyrow][4] = lotsizes[k]
                X_train[numpyrow][5] = garages[k]
                """
                data.append(names[k])
                data.append(prices[k])
                data.append(areas[k])
                data.append(bathrooms[k])
                data.append(bedrooms[k])
                data.append(lotsizes[k])
                data.append(garages[k])
                """
                print("Data")
                print(X_train[numpyrow])
                #X_train = np.append(X_train, data)
                y_train[numpyrow] = avgareas[k]
                numpyrow += 1
                #y_train = np.append(y_train, avgareas[k])
    print("CREATING TEST DATA!!!")
    nr = 0
    while len(testrand) < 8:
        print()
        print()
        print("Test rand size: "+ str(len(testrand)))
        
        rand = random.randint(0, 26)
        if ((rand not in trainrand) and (rand not in testrand)):
            testrand.append(rand)
            init = rand * 10
            for k in range (init, init + 10):
                data = []
                X_test[nr][0] = prices[k]
                X_test[nr][1] = areas[k]
                X_test[nr][2] = bathrooms[k]
                X_test[nr][3] = bedrooms[k]
                X_test[nr][4] = lotsizes[k]
                X_test[nr][5] = garages[k]
                """
                data.append(names[k])
                data.append(prices[k])
                data.append(areas[k])
                data.append(bathrooms[k])
                data.append(bedrooms[k])
                data.append(lotsizes[k])
                data.append(garages[k])
                """
                print("Data")
                print(X_test[nr])
                #X_test = np.append(X_test, data)
                y_test[nr] = avgareas[k]
                nr += 1
    
    print("X_train:")
    print(X_train)
    print(len(X_train))
    print("y_train:")
    print(y_train)
    print(len(y_train))
class KNeighborsClassifier(object):
    def __init__(self, n_neighbors=5, weights='uniform', p=2):
        self.n_neighbors = n_neighbors
        self.weights = weights
        self.p = p

    def fit(self, X, y):
        self.X = X
        self.y = y
        return self

    def _distance(self, data1, data2):
        """1: Manhattan, 2: Euclidean"""
        if self.p == 1:
            return sum(abs(data1 - data2))          
        elif self.p == 2:
            return np.sqrt(sum((data1 - data2)**2))
        raise ValueError("p not recognized: should be 1 or 2")

    def _compute_weights (self, distances):
        if self.weights == 'uniform':
            return [(1, y) for d, y in distances]
        elif self.weights == 'distance':
            matches = [(1, y) for d, y in distances if d == 0]
            return matches if matches else [(1/d, y) for d, y in distances]
        raise ValueError("weights not recognized: should be 'uniform' or 'distance'")

    def _predict_one(self, test):
        distances = sorted((self._distance(x, test), y) for x, y in zip(self.X, self.y))
        weights = self._compute_weights(distances[:self.n_neighbors])
        weights_by_class = defaultdict(list)
        for d, c in weights:
            weights_by_class[c].append(d)
        return max((sum(val), key) for key, val in weights_by_class.items())[1]

    def predict(self, X):
        return [self._predict_one(x) for x in X]

    def score(self, X, y):
        return sum(self.predict(X) == y) / len(y)

normalize()


X_train = np.empty([190,6])
y_train = np.empty(190)
X_test = np.empty([80,6])
y_test = np.empty(80)

createdata(X_train, y_train, X_test, y_test)


for K in range(25):
    K_value = K+1
    neigh = KNeighborsClassifier(n_neighbors = K_value, weights='uniform')
    neigh.fit(X_train, y_train) 
    y_pred = neigh.predict(X_test)
    print ("Accuracy is ")
    print(neigh.score(X_test,y_test)*100)
    print("% for K-Value:",K_value)


#neighbor = KNeighborsClassifier(n_neighbors=4)

#neighbor.fit(X_train, y_train)
"""
print()
print()
print("predictions: ")
print()
print(neighbor.predict(X_test))
print("The accuracy for this KNN algorithm is : ")
print(neighbor.score(X_test, y_test))

#y_pred = neighbor.predict(X_test) print "Accuracy is ", accuracy_score(y_test,y_pred)*100,"% for K-Value:",K_value
"""

"""
#createdata(X_train, y_train, X_test, y_test)
#print(neighbor._distance(X_train, X_test))
"""
