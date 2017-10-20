pysmac
======

**Deprecated -- we recommend to use [SMAC3](https://github.com/automl/SMAC3) for a Python implementation of SMAC.**

Simple python wrapper to [SMAC](http://www.cs.ubc.ca/labs/beta/Projects/SMAC/), a versatile tool for optimizing algorithm parameters.

SMAC is free for academic & non-commercial usage. Please contact Frank Hutter to discuss obtaining a license for commercial purposes.

This wrapper is intented to use SMAC directly from Python to minimize a objective function. It also contains some rudimentary analyzing tools that can also be applied to already existing SMAC runs.



Installation
------------

To install pysmac, we advise using the Python package management system:

.. code-block:: shell

        pip install git+https://github.com/automl/pysmac.git --user

If you prefer, you can clone the repository and install it manually via

.. code-block:: shell

        python setup.py install


Documentation
-----------

The documentation for pySMAC is hosted on http://pysmac.readthedocs.org/. It contains a `Quickstart guide <http://pysmac.readthedocs.org/en/latest/quickstart.html>`_ for the impatient, but also a detailed manual.
