import pysmac
import numpy
import bikeState
# import bikeSim
from constants import *


def new_state(bike, steerD, c1, c2, c3):
    """ Returns new bike state object after it passes through bike dynamics """
    # Get navigation command
    rhs_result = bike.rhs(steerD, c1, c2, c3)
    u = rhs_result[0]
    values = rhs_result[1]
    new_xB = bike.xB + values[0]*TIMESTEP
    new_yB = bike.yB + values[1]*TIMESTEP
    new_phi = bike.phi + values[2]*TIMESTEP
    new_psi = bike.psi+ values[3]*TIMESTEP
    new_delta = bike.delta + values[4]*TIMESTEP
    new_w_r = bike.w_r + values[5]*TIMESTEP
    new_v = bike.v + values[6]*TIMESTEP
    new_state = bikeState.Bike(new_xB, new_yB, new_phi, new_psi, new_delta, new_w_r, new_v)
    return new_state, new_w_r

def balance_score(c1, c2, c3):
    # taken from Navigation_node to initialize a new bike
    bike = bikeState.Bike(0, -10, 0.1, numpy.pi/3, 0, 0, 3.57)
    score = 0
    
    for _ in range(300):
        new_bike, phidot = new_state(bike,0,c1,c2,c3)
        bike.update(new_bike)
        score += phidot**2
    
    val=(numpy.sqrt(score))
    return(val)


parameters=dict(\
                # x1 is a continuous ('real') parameter between 235 and 245.
                # The default value/initial guess is the middle value 240.
                c1=('real', [0, 100], 50),
                
                # x2 is a continuous ('real') parameter between 125 and 135.
                # The default value/initial guess is the middle value 130.
                c2=('real', [100, 200], 150),
                
                # x3 is a continuous ('real') parameter between -10 and -.1.
                # The default value/initial guess is -5.
                c3=('real', [-10, 0], -5)
                )


# The next step is to create a SMAC_optimizer object
opt = pysmac.SMAC_optimizer()

# Then, call its minimize method with (at least) the three mandatory parameters
value, parameters = opt.minimize(
                                 balance_score, # the function to be minimized
                                 100,            # the number of function calls allowed
                                 parameters)    # the parameter dictionary


# the return value is a tuple of the lowest function value and a dictionary
# containing corresponding parameter setting.
print(('Lowest function value found: %f'%value))
print(('Parameter setting %s'%parameters))
